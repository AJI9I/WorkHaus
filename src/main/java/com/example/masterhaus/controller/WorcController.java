package com.example.masterhaus.controller;

import com.example.masterhaus.bot.MyBot;
import com.example.masterhaus.comparator.WorcsComparator;
import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.*;
import com.example.masterhaus.service.ManagerService;
import com.example.masterhaus.service.TelegramMessageService;
import com.example.masterhaus.service.UserService;
import com.example.masterhaus.service.WorcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class WorcController {
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    WorcRepo worcRepo;

    @Autowired
    AreasRepo areasRepo;

    @Autowired
    ManagerService managerService;

    @Autowired
    CitysRepo citysRepo;

    @Autowired
    SuburbsRepo suburbsRepo;

    @Autowired
    ManagersRepo managersRepo;

    @GetMapping("/manager/workadd")
    public String GWAdd(
      Model model
    ){
        Iterable<Worcs> wi = worcRepo.findAll();
        model.addAttribute("wi",wi);

        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);

        Citys citys = citysRepo.findById(managerService.getCityId()).get();
        Iterable<Areas> areas =  citys.getAreas();
        model.addAttribute("areas", areas);

        Iterable<Suburbs> suburbs = citys.getSuburbs();
        model.addAttribute("suburbs", suburbs);

        return "workadd";
    }


    @Autowired
    UserService userService;

    @PostMapping("/manager/workadd")
    public String PWAdd(
            @RequestParam(
                    name = "Name",
                    required = false,
                    defaultValue = "none") String Name,
            @RequestParam(
                    name = "FullName",
                    required = false,
                    defaultValue = "none") String FullName,
            @RequestParam(
                    name = "CategoryId",
                    required = false,
                    defaultValue = "none") String CategoryId,
            @RequestParam(
                    name = "Price",
                    required = false,
                    defaultValue = "none") String Price,
            @RequestParam(
                    name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            @RequestParam(
                    name = "AreaId",
                    required = false,
                    defaultValue = "none") String AreaId,
            @RequestParam(
                    name = "SuburbsId",
                    required = false,
                    defaultValue = "none") String SuburbsId,
            @RequestParam(
                    name = "Street",
                    required = false,
                    defaultValue = "none") String Street,
            @RequestParam(
                    name = "House",
                    required = false,
                    defaultValue = "none")String House,
            @RequestParam(
                    name = "Apartment",
                    required = false,
                    defaultValue = "none") String Apartment,
            @RequestParam(
                    name = "FIO",
                    required = false,
                    defaultValue = "none") String FIO,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        Worcs worcs = new Worcs(Name,FullName,Price,Phone,AreaId,Street,House,Apartment,FIO);

        Long MID = managerService.getMangersId();
        Managers managers = managersRepo.findById(MID).get();
//        worcs.setManagers(managers);

        Citys citys = citysRepo.findById(managerService.getCityId()).get();
        worcs.setCitys(citys);

        if(!SuburbsId.equals("none")&&!SuburbsId.equals("")){
            Long SID = Long.valueOf(SuburbsId);
            Suburbs suburbs = suburbsRepo.findById(SID).get();
            if(suburbs!=null){
                worcs.setSuburbs(suburbs);
            }
        }

        if(!AreaId.equals("none")&&!AreaId.equals("")){
            Long AID = Long.valueOf(AreaId);
            Areas areas = areasRepo.findById(AID).get();
            if(areas!=null){
                worcs.setAreas(areas);
            }
        }


        Categorys categorys = categoryRepo.findById(Long.valueOf(CategoryId)).get();
        worcs.setCategorys(categorys);
        worcs.setManagers(managers);
        worcs.setStatusname("Ожидает специалиста");
        worcs.setConfirmation(false);
        worcs =worcRepo.save(worcs);
        managers.getWorcs().add(worcs);
        managersRepo.save(managers);
        model.addAttribute("worcs", worcs);

        telegramMessageService.SendPersonInformation(managers,categorys);

        Iterable<Worcs> wi = worcRepo.findAll();
        model.addAttribute("wi",wi);

        Iterable<Areas> areas =  citys.getAreas();
        model.addAttribute("areas", areas);

        Iterable<Suburbs> suburbs = citys.getSuburbs();
        model.addAttribute("suburbs", suburbs);

        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        model.addAttribute("message", "Заказ успешно добавлен в базу №:" + worcs.getId());



        return "redirect:/manager/workview?wid="+worcs.getId();
    }


    /////////Поиск работы тут пиздец срач/////////////////
    @Autowired
    WorcsService worcsService;
    @GetMapping("/manager/workfind")
    public String GWfin(@RequestParam(
            name = "Phone",
            required = false,
            defaultValue = "none") String Phone,
                        @RequestParam(
            name = "wid",
            required = false,
            defaultValue = "none") String wid,
            Model model
    ){

        Phone=userService.getPhoneRegx(Phone);

        if(Phone.equals("none")&&!wid.equals("none")){
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }

        //конец гет запроса должного
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();


        Set<Worcs> worcs = managers.getWorcs();
        Long id = managers.getId();
        HashSet<Worcs> worcsPhone = new HashSet<>();

        for (Worcs w:worcs
             ) {
            if(w.getPhone().equals(Phone)||w.getId().equals(wid)){
                worcsPhone.add(w);
            }
        }


        Integer ws = worcsPhone.size();
        if(ws.equals(0)){
            //Если не найденно не одной работы то так, вывод всех работ
            List<Worcs> w = new LinkedList<>(worcs);
            Collections.reverse(w);
            model.addAttribute("worcs",worcsService.getManagersWorcs());
            model.addAttribute("message", "Для поиска заявки введите номер телефона");
            return "mworcfind";
        }

        if(ws.equals(1)){


            for (Worcs w:worcsPhone){
                Persons persons = null;
                persons = w.getPersons();
                if(persons != null){
                    model.addAttribute("person",persons);
                }
                model.addAttribute("worcs", w);
                return "mworcsview";
            }

        }
        if(ws>1){

            List<Worcs> w = new LinkedList<>(worcsPhone);
            Collections.reverse(w);

            model.addAttribute("worcsphone", w);
            return "mworcfind";
        }



        //Что такое?
        Iterable<Worcs> wi = worcRepo.findAll();
        model.addAttribute("wi",wi);

        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);

        Citys citys = citysRepo.findById(managerService.getCityId()).get();
        Iterable<Areas> areas =  citys.getAreas();
        model.addAttribute("areas", areas);

        Iterable<Suburbs> suburbs = citys.getSuburbs();
        model.addAttribute("suburbs", suburbs);

        return "workadd";
    }

    /////////Поиск работы тут пиздец срач/////////////////

    @PostMapping("/manager/workfind")
    public String PWfin(@RequestParam(
                                name = "wid",
                                required = false,
                                defaultValue = "none") String wid,
                        Model model
    ) {
        if (wid.equals("none")) {
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();


        Set<Worcs> worcs = managers.getWorcs();
        HashSet<Worcs> worcsId = new HashSet<>();

        Long WID = Long.valueOf(wid);
        for (Worcs w:worcs
        ) {
            if(w.getId().equals(WID)){
                worcsId.add(w);
            }
        }
        Integer ws = worcsId.size();
        if(ws.equals(1)){


            for (Worcs w:worcsId){

                Persons persons = null;
                persons = w.getPersons();
                if(persons != null){
                    model.addAttribute("person",persons);
                }
                model.addAttribute("worcs", w);
                return "redirect:/manager/workview?wid="+w.getId();
            }

        }
        model.addAttribute("message", "Введите номер телефона");
        return "mworcfind";
    }
    /////////Поиск работы тут пиздец срач/////////////////

    /////////Информация о работе/////////////////

    @GetMapping("/manager/workview")
    public String PWVie(@RequestParam(
            name = "wid",
            required = false,
            defaultValue = "none") String wid,
                        Model model
    ) {
        if (wid.equals("none")) {
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();


        Set<Worcs> worcs = managers.getWorcs();
        HashSet<Worcs> worcsId = new HashSet<>();

        Long WID = Long.valueOf(wid);
        for (Worcs w:worcs
        ) {
            if(w.getId().equals(WID)){
                worcsId.add(w);
            }
        }
        Integer ws = worcsId.size();
        if(ws.equals(1)){


            for (Worcs w:worcsId){

                Persons persons = null;
                persons = w.getPersons();
                if(persons != null){
                    model.addAttribute("person",persons);
                }
                model.addAttribute("worcs", w);
                return "mworcsview";
            }

        }
        return "mworcsview";
    }



    /////////Информация о работе/////////////////


    //////////Начало/////////////////////////////////////Отмена заявки/////////////////////////////////////////////////
    @PostMapping("/manager/worcstop")
    public String WS(@RequestParam(
            name = "wid",
            required = false,
            defaultValue = "none") String wid,
                        Model model
    ) {
        if (wid.equals("none")) {
            model.addAttribute("message", "нет ид запроса");
            return "mworcfind";
        }
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();
        Set<Worcs> worcs = null;
        worcs = managers.getWorcs();

        if(worcs==null){
            model.addAttribute("message", "Работа не найдена");
            return "mworcfind";
        }

        Worcs ww = null;
        for (Worcs w: worcs
             ) {
            if(w.getId().equals(Long.valueOf(wid))){
                w.setStatusname("Отмена менеджером");
                w.setStatus(false);
                ww =worcRepo.save(w);
            }
        }

        Persons persons = null;
        persons = ww.getPersons();
        if(persons != null){
            model.addAttribute("person",persons);
        }

        model.addAttribute("worcs", ww);

        return "redirect:/manager/workview?wid="+ww.getId();
    }
    //////////Конец/////////////////////////////////////Отмена заявки/////////////////////////////////////////////////

    @Autowired
    DisputRepo disputRepo;

    //////////Начало/////////////////////////////////////Запуск заявки/////////////////////////////////////////////////
    @PostMapping("/manager/worcstart")
    public String WStart(@RequestParam(
            name = "wid",
            required = false,
            defaultValue = "none") String wid,
                     Model model
    ) {
        if (wid.equals("none")) {
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();
        Set<Worcs> worcs = null;
        worcs = managers.getWorcs();

        if(worcs==null){
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }

        Worcs ww = null;
        for (Worcs w: worcs
        ) {
            if(w.getId().equals(Long.valueOf(wid))){
                w.setStatusname("Ожидает специалиста");
                w.setConfirmation(false);
                    if(w.getPersons()!=null){
                    Disput disput = new Disput();
                    disput.setPersons(w.getPersons());
                    disput.setName("Запуск заявки менеджером");
                    disput =disputRepo.save(disput);
                    w.setDisput(disput);
                    }
                w.setStatus(true);
                w.setPersons(null);
                ww =worcRepo.save(w);
            }
        }

        Persons persons = null;
        persons = ww.getPersons();
        if(persons != null){
            model.addAttribute("person",persons);
        }

        model.addAttribute("worcs", ww);

        return "redirect:/manager/workview?wid="+ww.getId();
    }
    //////////Конец/////////////////////////////////////Запуск заявки/////////////////////////////////////////////////

    //////////Начала/////////////////////////////////////Отмена заявки и возврат средств/////////////////////////////////////////////////
    @PostMapping("/manager/worcstotsellby")
    public String WSSellBy(@RequestParam(
            name = "wid",
            required = false,
            defaultValue = "none") String wid,
                         Model model
    ) {
        if (wid.equals("none")) {
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();
        Set<Worcs> worcs = null;
        worcs = managers.getWorcs();

        if(worcs==null){
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }
        String chatid = "";
        Worcs ww = null;
        for (Worcs w: worcs
        ) {
            if(w.getId().equals(Long.valueOf(wid))){
                w.setStatusname("Отмена с возвратом");
                w.setStatus(false);

                if(!managerService.returnByWorcPerson(w)){
                    model.addAttribute("message", "Введите номер телефона");
                    return "redirect:/manager/workview?wid="+w.getId();
                }
                chatid = w.getPersons().getTelegramuser().getChatid();
//                w.setPersons(null);
                ww =worcRepo.save(w);
            }
        }

        Persons persons = null;
        persons = ww.getPersons();
        if(persons != null){
            model.addAttribute("person",persons);
        }

        model.addAttribute("worcs", ww);



        String message = "Заявка №: " + ww.getId()+"\n"+
                "Наименование: " + ww.getName() + "\n"+
                "По улице:" + ww.getStreet()+ "\n"+
                "Дом: " + ww.getHouse()+ "\n"+
                "Квартира: " + ww.getApartment()+ "\n"+
                "<b>Отменена</b>, по заявку произведен возврат средств"+ "\n"+
                "<b>Ваш баланс:</b> " + persons.getCashaccounts().getCash()+" р";
        model.addAttribute("worcs", ww);
        telegramMessageService.sendEvent(chatid,message);

        return "redirect:/manager/workview?wid="+ww.getId();
    }
    //////////Конец/////////////////////////////////////Отмена заявки и возврат средств/////////////////////////////////////////////////

    //////////Начало/////////////////////////////////////Подтверждение заязвзки список работ/////////////////////////////////////////////////
    @GetMapping("/manager/worcconformationlist")
    public String GWC(
            Model model
    ){

        Managers managers = managerService.getManagers();
        Set<Worcs> worcs = managers.getWorcs();
        List<Worcs> worcsList = worcs.stream()
                .filter(p->p.getStatusname().equals("В работе")&&p.isConfirmation()==false)
                .sorted(new WorcsComparator())
                .collect(Collectors.toList());
        model.addAttribute("worcsphone", worcsList);
        return "mworcslist";
    }
    //////////Конец /////////////////////////////////////Подтверждение заязвзки список работ/////////////////////////////////////////////////


    @Autowired
    TelegramMessageService telegramMessageService;
    //////////Начало/////////////////////////////////////Подтверждение заязвзки /////////////////////////////////////////////////
    @PostMapping("/manager/worcsconformation")
    public String WC(@RequestParam(
            name = "wid",
            required = false,
            defaultValue = "none") String wid,
                           Model model
    ) throws IOException {
        if (wid.equals("none")) {
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }
        Managers managers = managersRepo.findById(managerService.getMangersId()).get();
        Set<Worcs> worcs = null;
        worcs = managers.getWorcs();

        if(worcs==null){
            model.addAttribute("message", "Введите номер телефона");
            return "mworcfind";
        }

        Worcs ww = null;
        for (Worcs w: worcs
        ) {
            if(w.getId().equals(Long.valueOf(wid))){

                w.setConfirmation(true);
                ww =worcRepo.save(w);
                ww=w;
            }
        }

        Persons persons = null;
        persons = ww.getPersons();
        if(persons != null){
            model.addAttribute("person",persons);
        }
        String chatid = persons.getTelegramuser().getChatid();

        String message = "Заявка №: " + ww.getId()+"\n"+
                "Наименование: " + ww.getName() + "\n"+
                "По улице:" + ww.getStreet()+ "\n"+
                "Дом: " + ww.getHouse()+ "\n"+
                "Квартира: " + ww.getApartment()+ "\n"+
                "<b>Подтвержденна</b>, после выполнения не забудьте закрыть заявку";
        model.addAttribute("worcs", ww);
        telegramMessageService.sendEvent(chatid,message);
        return "redirect:/manager/workview?wid="+ww.getId();
    }
    //////////Конец /////////////////////////////////////Подтверждение заязвзки /////////////////////////////////////////////////


    @GetMapping("/t")
    public String f(@RequestParam(
            name = "t",
            required = false,
            defaultValue = "none") String t,
            Model model
    )
    {if(t.equals("none")){
        telegramMessageService.sendEvent("651381119","Я научился, принудительно заебывать всех через телеграм а не через Callback service :)");
    }
        return "/";
    }
}
