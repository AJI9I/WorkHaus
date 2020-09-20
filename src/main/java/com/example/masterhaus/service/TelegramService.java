package com.example.masterhaus.service;

import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TelegramService {

    @Autowired
    private WorcRepo worcRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TelegramUserRepo telegramUserRepo;

    @Autowired
    private PersonsRepo personsRepo;

    @Autowired
    CashaccountsRepo cashaccountsRepo;

    @Autowired
    PaymenthistorysRepo paymenthistorysRepo;

    @Autowired
    CitysRepo citysRepo;

    public List<Categorys> getFatherCategory(String chatId){

        List<Categorys> father = new ArrayList<>();

        Managers managers = telegramUserRepo.findByChatid(chatId).get().getPersons().getManagers();

        Set<Worcs> worcs = managers.getWorcs();

        for (Worcs w: worcs
             ) {


            if(w.getStatusname().equals("Ожидает специалиста")){

                Long fa = Long.valueOf(w.getCategorys().getFather());
                Categorys categorysF = categoryRepo.findById(fa).get();

                if(father.indexOf(categorysF)== -1){
                    father.add(categorysF);
                }
            }
        }

        return father;
    }

    public ArrayList<Categorys> getChildCategory(Long father, String chatId){


        ArrayList<Categorys> child = new ArrayList<>();

        Managers managers = telegramUserRepo.findByChatid(chatId).get().getPersons().getManagers();

        Set<Worcs> worcs = managers.getWorcs();

        for (Worcs w: worcs
        ) {
            if (w.getStatusname().equals("Ожидает специалиста")) {
                Long IDC = Long.valueOf(w.getCategorys().getFather());
                if(IDC.equals(father)) {
                    Categorys categorys = w.getCategorys();
                    if(child.indexOf(categorys)== -1){
                        child.add(categorys);
                    }

                }
            }
        }

        return child;
    }
    public Categorys getCategoryId(Long id){
        Categorys cat = categoryRepo.findById(id).get();
        return cat;
    }

    public List<Worcs> getWorksCategory(Long id,String chatId)
    {
        List<Worcs> wo = new ArrayList<>();

        Managers managers = telegramUserRepo.findByChatid(chatId).get().getPersons().getManagers();

        Set<Worcs> worcs = managers.getWorcs();

        for (Worcs w: worcs
        ) {
            if (w.getStatusname().equals("Ожидает специалиста")) {
                Long IDC = Long.valueOf(w.getCategorys().getId());
                if(IDC.equals(id)) {
                    if(wo.indexOf(w)== -1){
                        wo.add(w);
                    }

                }
            }
        }

        return wo;
    }

    public Worcs getWorksId(Long id){
        Worcs w = worcRepo.findById(id).get();
        return w;
    }

    public Set<Worcs> getWorcsInCity(String chatId){
        Set<Worcs> w = null;
        Citys citys = telegramUserRepo.findByChatid(chatId).get().getCitys();
        int i = 0;
        Set<Worcs> worcs = citys.getWorcs();
        if(worcs.size()>10){
            w =worcs.stream().limit(10).collect(Collectors.toSet());
        }else {
            w = worcs;
        }
        return w;
    }

    public List<Citys> getCitysManagers(){
        Iterable<Citys> citys = citysRepo.findAll();
        ArrayList<Citys> citysArrayList = new ArrayList<>();

        for (Citys c:citys
             ) {
            if(c.getManagers()!=null){
                citysArrayList.add(c);
            }
        }
        return citysArrayList;
    }

    public void setChatId(String chatid, String name){
        Optional<Telegramuser> telegramuserOptional = telegramUserRepo.findByChatid(chatid);
        if (telegramuserOptional.isPresent()) {
            if(telegramuserOptional.get().getTgname()==null)
            {
                telegramuserOptional.get().setTgname(name);
                telegramUserRepo.save(telegramuserOptional.get());

            }
            return;
        }else {
            Telegramuser telegramUser = new Telegramuser();
            telegramUser.setChatid(chatid);
            telegramUser.setAutoreise(false);
            telegramUserRepo.save(telegramUser);
        }

    }

    public void setCitysUser(Long id, String chatId){
        Telegramuser telegramuser = null;
        telegramuser = telegramUserRepo.findByChatid(chatId).get();
        if(telegramuser==null){
            return;
        }
        Citys citys = null;
        citys = citysRepo.findById(id).get();
        if(citys == null){
            return;
        }
        telegramuser.setCitys(citys);
        telegramUserRepo.save(telegramuser);
    }

    public Set<Managers> getManagerCity(Long id){
        return citysRepo.findById(id).get().getManagers();
    }

    //Проверка привязан ли акккаунт телеграмма к специалисту
    public boolean getIsPersons(String chatid){

        Telegramuser telegramuser = null;
        telegramuser = telegramUserRepo.findByChatid(chatid).get();
        if(telegramuser == null){
            return false;
        }

        Persons persons = null;
        persons = telegramuser.getPersons();
        if(persons == null){
            return false;
        }
        return true;
    }
    //Получает профиль человека
    public Persons getPersons(String chatid){

        Telegramuser telegramuser = telegramUserRepo.findByChatid(chatid).get();
        Persons persons = telegramuser.getPersons();
        return persons;
    }

    public boolean Pay(String chatId, Long wid){

        Worcs wo = null;

        Managers managers = telegramUserRepo.findByChatid(chatId).get().getPersons().getManagers();

        Set<Worcs> worcss = managers.getWorcs();

        for (Worcs w: worcss
        ) {
            if (w.getStatusname().equals("Ожидает специалиста")) {
                Long IDC = Long.valueOf(w.getId());
                if(IDC.equals(wid)) {
                      wo = w;
                }
            }
        }
        if(wo != null){
            Persons persons = getPersons(chatId);
            Worcs worcs = wo;
            Categorys categorys = worcs.getCategorys();

            Integer pricew = getPercent(worcs.getPrice(),categorys.getPercent());
            Integer cash = persons.getCashaccounts().getCash();
            int val = cash.compareTo(pricew);
            if(val==1||val==0){
                setPayments(persons,pricew,wid);
                //Установка статуса в работе
                setPersonsWorc(persons,wid);

                return true;
            }
        }
        return false;
    }

    public List<Worcs> getMyCategoryWorcs(String chatId){
        Managers managers = telegramUserRepo.findByChatid(chatId).get().getPersons().getManagers();
        Persons persons = telegramUserRepo.findByChatid(chatId).get().getPersons();

        Set<Worcs> worcs = managers.getWorcs();
        Set<Categorys> personCatogorys = null;

        List<Worcs> worcsList = new ArrayList<>();

        personCatogorys = persons.getCategorys();
        if(personCatogorys!=null){
            for (Worcs w: worcs
                 ) {
                if (w.getStatusname().equals("Ожидает специалиста")) {

                    Long IDC = Long.valueOf(w.getCategorys().getId());
                    for (Categorys c: personCatogorys
                         ) {
                        Long IDPC = c.getId();
                        if(IDPC.equals(IDC)){
                            if(worcsList.indexOf(w)== -1){
                                worcsList.add(w);
                            }
                        }
                    }
                }
            }
        }
        return worcsList;
    }


    public List<Worcs> getMyOpenWorcs(String chatId) {
        Persons persons = telegramUserRepo.findByChatid(chatId).get().getPersons();
        Set<Worcs> worcs = persons.getWorcs();

        List<Worcs> worcsList = new ArrayList<>();

        for (Worcs w : worcs
        ) {
            if (w.getStatusname().equals("В работе")) {
                if (worcsList.indexOf(w) == -1) {
                    worcsList.add(w);
                }

            }

        }
        return worcsList;
    }

    public Worcs getPersonCloseWorcs(String chatId, Long Wid){

        Persons persons = telegramUserRepo.findByChatid(chatId).get().getPersons();

        Set<Worcs> worcsSet = persons.getWorcs();
        Worcs worcs = null;

        for (Worcs w : worcsSet
        ) {
            if (w.getStatusname().equals("В работе")) {

                Long WID = w.getId();
                if(WID.equals(Wid)){

                    worcs=w;

                    worcs.setStatusname("Закрыта");
                    worcs.setStatus(false);
                    worcRepo.save(worcs);
                    return worcs;
                }

            }

        }
        return worcs;
    }

    public Worcs getPersonWorc(String chatId, Long Wid){
        Persons persons = telegramUserRepo.findByChatid(chatId).get().getPersons();

        Set<Worcs> worcsSet = persons.getWorcs();
        Worcs worcs = null;

        for (Worcs w : worcsSet
        ) {
            if (w.getStatusname().equals("В работе")) {

                Long WID = w.getId();
                if (WID.equals(Wid)) {

                    worcs = w;
                    return worcs;
                }

            }
        }
        return worcs;
    }

    private void setPersonsWorc(Persons persons, Long wid){

        Worcs worcs = worcRepo.findById(wid).get();

        worcs.setStatusname("В работе");
        worcs.setPersons(persons);
        worcRepo.save(worcs);

    }



    @Transactional
    public Areas getAreasWorc(Long wid){
        return worcRepo.findById(wid).get().getAreas();
    }
    @Transactional
    public Suburbs getSuburbsWorc(Long wid){
        return worcRepo.findById(wid).get().getSuburbs();
    }

    //Платежные операции при покупке работы
    private void setPayments(Persons persons, int pricew,Long wid){

            Cashaccounts cashaccounts = persons.getCashaccounts();

            int cash = cashaccounts.getCash();
            cash = cash - pricew;
            cashaccounts.setCash(cash);
            cashaccountsRepo.save(cashaccounts);

            Paymenthistorys paymenthistorys = new Paymenthistorys();
            paymenthistorys.setPersons(persons);
            paymenthistorys.setDowncash(pricew);
            paymenthistorys.setCash(cash);
            paymenthistorys.setOperation("down_buy_worc_:"+wid);
            paymenthistorysRepo.save(paymenthistorys);

    }

    public int getPercent(double price, double percent){
        double i = price/100;
        double f = i*percent;
        int b = (int)f;
        return b;
    }

    public boolean getIsCitys(String chatId){
        Telegramuser telegramuser = null;
        telegramuser = telegramUserRepo.findByChatid(chatId).get();
        if(telegramuser != null){
            Citys citys = null;
            citys = telegramuser.getCitys();
            if(citys!=null){
                return true;
            }
        }
        return false;
    }

    ////////////////////////Отправка сообщений с сайта менеджерам телеграмм//////////////////////////////////

    @Autowired TelegramMessageService telegramMessageService;
    public void sendMessageManager(String message,String Phone,String name, Citys citys){

        Set<Managers> managers = citys.getManagers();

        for (Managers m:managers){
            Telegramuser tgu = m.getTelegramuser();
            if(m.getTelegramuser()!=null){

                telegramMessageService.sendManager(m.getTelegramuser().getChatid(),message,Phone,name);
            }
        }
    }
    ////////////////////////Отправка сообщений с сайта менеджерам телеграмм//////////////////////////////////

}
