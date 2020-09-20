package com.example.masterhaus.controller;

import com.example.masterhaus.comparator.PaymenthistorysComparator;
import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.CategoryRepo;
import com.example.masterhaus.repos.ManagersRepo;
import com.example.masterhaus.repos.PersonsRepo;
import com.example.masterhaus.repos.TelegramUserRepo;
import com.example.masterhaus.service.ManagerService;
import com.example.masterhaus.service.PersonService;
import com.example.masterhaus.service.UserService;
import com.example.masterhaus.service.WorcsService;
import org.glassfish.grizzly.utils.ArraySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ManagerPersonController {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    PersonsRepo personsRepo;

    @Autowired
    ManagerService managerService;

    @Autowired
    ManagersRepo managersRepo;

    @Autowired
    TelegramUserRepo telegramUserRepo;

    @Autowired
    PersonService personService;



    @GetMapping("/manager/personadd")
    private String GPersAdd(Model model){

        model.addAttribute("musername",managerService.getUsername());

//        Managers Managers = managersRepo.findById(managerService.getMangersId()).get();
//        Iterable<Persons> persons = Managers.getPersons();

        model.addAttribute("persons",personService.getLast10Persons());

//        Iterable<Categorys> cat = categoryRepo.findAll();
//        model.addAttribute("cat",cat);
        return "mpersonadd";
    }

    @Autowired
    UserService userService;

    @PostMapping("/manager/personadd")
    public String PPersAdd(
            @RequestParam (name = "Name",
                    required = false,
                    defaultValue = "none") String Name,
            @RequestParam (name = "Family",
                    required = false,
                    defaultValue = "none") String Family,
            @RequestParam (name = "Surname",
                    required = false,
                    defaultValue = "none") String Surname,
            @RequestParam (name = "Phone",
            required = false,
            defaultValue = "none") String Phone,
            @RequestParam (name = "TeleIdChat",
                    required = false,
                    defaultValue = "none") String TeleIdChat,
            Model model
    ){

        Phone=userService.getPhoneRegx(Phone);


        model.addAttribute("musername",managerService.getUsername());

        Managers Managers = managersRepo.findById(managerService.getMangersId()).get();
//        Iterable<Persons> persons = Managers.getPersons();

        model.addAttribute("persons",personService.getLast10Persons());


        if(Name.equals("none")||Family.equals("none")||Surname.equals("none")||Phone.equals("none"))
        {
            model.addAttribute("message","Заполните форму в соответствии со здравым смыслом");
            return "mpersonadd";
        }

        Persons pps = personsRepo.findByPhone(Phone);
        if( pps != null){
            model.addAttribute("message","Такой специалист уже присутствует в системе");
            return "mpersonadd";
        }

        Long MID = managerService.getMangersId();
        Managers managers = managersRepo.findById(MID).get();
        if(managers==null){
            model.addAttribute("message","Проблема связи с менеджерским аккаунтом, проблема аутентификации");
            return "mpersonadd";
        }

        Persons psons = new Persons(Name,Surname,Family,Phone);
        psons.setManagers(managers);
        personsRepo.save(psons);

        model.addAttribute("message", "Специалист успешно добавленн");

        Telegramuser telegramuser = null;
        telegramuser = telegramUserRepo.findByChatid(TeleIdChat).orElse(null);

            if(telegramuser==null){

                model.addAttribute("message", "Специалист добавлен проблема с телеграмм ИД");
                return "redirect:/manager/personadd";
            }
            telegramuser=telegramUserRepo.findByChatid(TeleIdChat).get();
            psons.setTelegramuser(telegramuser);
            personsRepo.save(psons);

        model.addAttribute("message", "Проблема с подключение TelegrammId, возможно вы его не указали, это можно будет исправить в профиле специалиста");
        return "redirect:/manager/personadd";
    }


    @PostMapping("/manager/personview")
    public String GPersV(
            @RequestParam(
                    name = "pid",
                    required = false,
                    defaultValue = "none") String pid,
            @RequestParam(
            name = "Phone",
            required = false,
            defaultValue = "none") String Phone,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        if(pid.equals("none")&&Phone.equals("none")){
            model.addAttribute("message","Для поиска специалиста введите номер телефона");
            return "redirect:/manager/personfind";
        }

        Managers Managers = managersRepo.findById(managerService.getMangersId()).get();
        Iterator<Persons> persons = Managers.getPersons().iterator();
        Long PID=1L;
        if(!pid.equals("none")){
            PID= Long.valueOf(pid);
        }

        Persons pers = new Persons();
        while(persons.hasNext())
        {
            Persons p = persons.next();
            if(!pid.equals("none")){
                long id = p.getId();
                if(id==PID)
                {
                    pers = p;
                    model.addAttribute("persons",personService.getLast10Persons());
                    model.addAttribute("pers", pers);
                    int cash = 0;
                    if(pers.getCashaccounts()!=null){
                        cash = pers.getCashaccounts().getCash();
                    }
                    model.addAttribute("cash", cash);

                    return "mpersonview";
                }
            }
            if(Phone!="none"){
                String ph = p.getPhone();
                if(ph.equals(Phone))
                {
                    pers = p;
                    model.addAttribute("persons",personService.getLast10Persons());
                    model.addAttribute("pers", pers);
                    int cash = 0;
                    if(pers.getCashaccounts()!=null){
                        cash = pers.getCashaccounts().getCash();
                    }
                    model.addAttribute("cash", cash);

                    return "mpersonview";
                }
            }

        }

        model.addAttribute("message","Специалист не найден");
        return "mpersonfind";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/personfind")
    public String GPersFind(
                       Model model){
//        Managers Managers = managersRepo.findById(managerService.getMangersId()).get();
        model.addAttribute("persons",personService.getLast10Persons());


        return "mpersonfind";
    }


    @GetMapping("/manager/personedit")
    public String GPersEnd(
            @RequestParam(
                    name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        Persons pps = null;
        if(Phone.equals("none")){
            model.addAttribute("message","Для поиска специалиста введите номер телефона");
            return "mpersonfind";
        }
        else{

                pps = personsRepo.findByPhone(Phone);

                Long mid = managerService.getMangersId();

                if (pps != null) {
                    // Переделать првоерку менеджера
                    if(pps.getManagers()==null){
                        model.addAttribute("message", "Специалист с таким номером телефона не найден");
                        return "mpersonfind";
                    }
                    Long personM = pps.getManagers().getId();
                    if (personM.equals(mid)){
                        Iterable<Categorys> ccat = categoryRepo.findAll();
                        Iterable<Categorys> pi = pps.getCategorys();
//                        Paymenthistorys paymenthistorys = null;
                        List<Paymenthistorys> paymenthistorys = pps.getPaymenthistorys();
                        if(paymenthistorys != null){

                            paymenthistorys = paymenthistorys.stream().sorted(new PaymenthistorysComparator()).collect(Collectors.toList());
                            Collections.reverse(paymenthistorys);
                            model.addAttribute("paymenthistorys",paymenthistorys);
                        }

                        model.addAttribute("pi", pi);

                        model.addAttribute("ccat", ccat);
                        model.addAttribute("pps", pps);

                    }

                } else {
                    model.addAttribute("message", "Специалист с таким номером телефона не найден");
                    return "mpersonfind";
                }
        }
        return "mpersonedit";
    }





    @PostMapping("/manager/personedit")
    public String PPersFind(
            @RequestParam(
                    name = "PersonId",
                    required = false,
                    defaultValue = "none") String PersonId,
            @RequestParam(
                    name = "Name",
                    required = false,
                    defaultValue = "none") String Name,
            @RequestParam(
                    name = "Family",
                    required = false,
                    defaultValue = "none") String Family,
            @RequestParam(
                    name = "Surname",
                    required = false,
                    defaultValue = "none") String Surname,
            @RequestParam(
                    name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            @RequestParam(
                    name = "TeleId",
                    required = false,
                    defaultValue = "none") String TeleId,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        if(PersonId.equals("none")){
            return "mpersonfind";
        }

        Iterable<Categorys> ccat = categoryRepo.findAll();
        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();

        if(!managerService.isPersonsManager(pps)){
            return "mpersonfind";
        }

        Managers managers = managersRepo.findById(managerService.getMangersId()).get();


        pps.setFamily(Family);
        pps.setName(Name);
        pps.setSurname(Surname);
        pps.setPhone(Phone);
        personsRepo.save(pps);

        Telegramuser telegramuser = telegramUserRepo.findByChatid(TeleId).orElse(null);

        if(telegramuser!=null){
            telegramuser.setCitys(managers.getCitys());
            telegramUserRepo.save(telegramuser);
            model.addAttribute("message", "Информация отредактированна, телеграмм ID привязан");
            pps.setTelegramuser(telegramuser);
            personsRepo.save(pps);


        }

        List<Paymenthistorys> paymenthistorys = pps.getPaymenthistorys();
        if(paymenthistorys != null){

            paymenthistorys.stream().sorted(new PaymenthistorysComparator());
            Collections.reverse(paymenthistorys);
            model.addAttribute("paymenthistorys",paymenthistorys);
        }

        Iterable<Categorys> pi = pps.getCategorys();
        model.addAttribute("pi",pi);

        model.addAttribute("message","Информация отредактированна");

        model.addAttribute("ccat", ccat);
        model.addAttribute("pps",pps);

        return "redirect:/manager/personedit?Phone="+Phone;
    }

    ////////////Начало////////////////Отвязать телеграмм ИД////////////////////////////////////////////////
    @PostMapping("/manager/telegrammdel")
    public String PPersFind(
            @RequestParam(
                    name = "PersonId",
                    required = false,
                    defaultValue = "none") String PersonId,
            @RequestParam(
                    name = "TeleId",
                    required = false,
                    defaultValue = "none") String TeleId,
            Model model){

        if(PersonId.equals("none")){
            return "mpersonfind";
        }
        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();

        if(!managerService.isPersonsManager(pps)){
            return "mpersonfind";
        }
        pps.setTelegramuser(null);
        personsRepo.save(pps);
        return "redirect:/manager/personedit?Phone="+pps.getPhone();
    }
    ////////////Конец ////////////////Отвязать телеграмм ИД////////////////////////////////////////////////

    ////////////Начало////////////////Работа с категориями специалиста////////////////////////////////////////////////
    @PostMapping("/manager/addcategoryperson")
    public String addCatPers(
            @RequestParam Map<String,String> form,
            @RequestParam String PersonId,
            @RequestParam String Phone,
            Model model
    ){

        Phone=userService.getPhoneRegx(Phone);

        if(form.size()==0 || PersonId=="none"){
            return "personfind";
        }

        Persons persons = personsRepo.findById(Long.valueOf(PersonId)).get();

        Iterable<Categorys> categorys = categoryRepo.findAll();
        HashSet<String> catname = new HashSet<>();

        for (Categorys c: categorys) {
            catname.add(c.getName());
        }

        Iterable<Categorys> categorysP = persons.getCategorys();
        HashSet<String> catnameP = new HashSet<>();

        for (Categorys c: categorysP) {
            catnameP.add(c.getName());
        }



        for(String key:form.keySet()){
            if(catname.contains(key)){
                if(!catnameP.contains(key)){
                    Long CID = Long.valueOf(form.get(key));
                    Categorys categorys1 = categoryRepo.findById(CID).get();
                    persons.getCategorys().add(categorys1);
                    personsRepo.save(persons);
                }
            }
        }

        List<Paymenthistorys> paymenthistorys = persons.getPaymenthistorys();
        if(paymenthistorys != null){
            Collections.reverse(paymenthistorys);
            paymenthistorys.stream().sorted(new PaymenthistorysComparator());
            model.addAttribute("paymenthistorys",paymenthistorys);
        }

        Iterable<Categorys> pi = personsRepo.findById(Long.valueOf(PersonId)).get().getCategorys();
        model.addAttribute("pi",pi);

        Iterable<Categorys> ccat = categoryRepo.findAll();
        model.addAttribute("ccat", ccat);
        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();
        model.addAttribute("pps",pps);

        return "redirect:/manager/personedit?Phone="+Phone;
    }

    @PostMapping("/manager/dellcategoryperson")
    public String DelCatPers(
            @RequestParam String CategoryId,
            @RequestParam String PersonId,
            @RequestParam String Phone,
            Model model
    ){

        Phone=userService.getPhoneRegx(Phone);

        Persons pp = personsRepo.findById(Long.valueOf(PersonId)).get();
        Categorys cc = categoryRepo.findById(Long.valueOf(CategoryId)).get();

        pp.getCategorys().remove(cc);
        personsRepo.save(pp);

        Iterable<Categorys> pi = personsRepo.findById(Long.valueOf(PersonId)).get().getCategorys();
        model.addAttribute("pi",pi);

        List<Paymenthistorys> paymenthistorys = pp.getPaymenthistorys();
        if(paymenthistorys != null){
            Collections.reverse(paymenthistorys);
            paymenthistorys.stream().sorted(new PaymenthistorysComparator());
            model.addAttribute("paymenthistorys",paymenthistorys);
        }


        Iterable<Categorys> ccat = categoryRepo.findAll();
        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();
        model.addAttribute("ccat", ccat);
        model.addAttribute("pps",pps);
        return "redirect:/manager/personedit?Phone="+Phone;
    }
    ////////////Конец////////////////Работа с категориями специалиста////////////////////////////////////////////////
@Autowired
    WorcsService worcsService;
    ////////////Начало////////////////Работа с заявками специалиста////////////////////////////////////////////////
    @GetMapping("/manager/personworc")
    public String PW(
            @RequestParam(
                    name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model) {

        Phone=userService.getPhoneRegx(Phone);

        Persons persons = null;

        if (Phone.equals("none")) {
            model.addAttribute("message", "Для поиска специалиста введите номер телефона");
            return "mpersonfind";
        } else {

            persons = personsRepo.findByPhone(Phone);

            if (!managerService.isPersonsManager(persons)) {
                model.addAttribute("worcs", worcsService.getManagersWorcs());
                return "mpersonfind";
            }

//            Set<Worcs> worcs  = persons.getWorcs();
//            List<Worcs> worcsList = new ArrayList<>(worcs);
//                    Collections.reverse(worcsList);
            model.addAttribute("worcsphone", worcsService.getPersonsWorcs(persons));

            return "mworcslist";
        }

    }
    ////////////Конец////////////////Работа с заявками специалиста////////////////////////////////////////////////
}
