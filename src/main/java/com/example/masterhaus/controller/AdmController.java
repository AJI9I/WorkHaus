package com.example.masterhaus.controller;

import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.*;
import com.example.masterhaus.service.AdmService;
import com.example.masterhaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.POST;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdmController {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    UserService userService;

    @Autowired
    AdmService admService;

    @Autowired
    ManagersRepo managersRepo;

    @Autowired
    CitysRepo citysRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/administration/mmanageradd")
    public String gc(@RequestParam(name = "message",
            required = false,
            defaultValue = "") String message,
                     Model model){

        Iterable<Citys> citys = citysRepo.findAll();
        if(citys==null)
        {
            model.addAttribute("message", "Перед тем как добавить менеджера, добаьте соответствующий город");
            return "admmanageradd";
        }
        model.addAttribute("message", message);
        model.addAttribute("citys",citys);
        return "admmanageradd";
    }


    @PostMapping("/administration/mmanageradd")
    public String gg(@RequestParam (name = "firstname",
            required = false,
            defaultValue = "none") String firstname,
                     @RequestParam (name = "lastname",
                             required = false,
                             defaultValue = "none")String lastname,
                     @RequestParam (name = "familyname",
                             required = false,
                             defaultValue = "none")String familyname,
                     @RequestParam (name = "phone",
                             required = false,
                             defaultValue = "none")String phone,
            @RequestParam (name = "login",
                    required = false,
                    defaultValue = "none")String login,
            @RequestParam (name = "password",
                    required = false,
                    defaultValue = "none")String password,
                     @RequestParam (name = "CityName",
                             required = false,
                             defaultValue = "none")String CityName,
            Model model){

        if(login=="none"|| firstname == "none"|| lastname == "none"|| familyname == "none"|| phone == "none"|| password == "none"|| CityName == "none"){
            model.addAttribute("message","Заполните форму должным образом");
            return "redirect:/administration/mmanageradd";
        }
        phone=userService.getPhoneRegx(phone);

        Users users = new Users();
        users.setUsername(login);
        users.setPassword(password);
        users.setActive(true);
        if(!userService.saveUser(users)){
            model.addAttribute("message","Не понятная причина ошибка? не прошел проверку оригинальности");
            return "redirect:/administration/mmanageradd";
        }


        Managers managers = new Managers();
        managers.setPhone(phone);
        managers.setLastname(lastname);
        managers.setFamilyname(familyname);
        managers.setFirstname(firstname);
        managers.setDostup(true);
        managers.setUsers(userService.findUsersByName(login));

        Citys ccitys = citysRepo.findById(Long.valueOf(CityName)).get();
        if(ccitys == null){
            model.addAttribute("message","Что то вы с городом указали не то");
            return "redirect:/administration/mmanageradd";
        }

        managers.setCitys(ccitys);
        if(!admService.addNewmanagers(managers))
        {
            model.addAttribute("message","Не понятная причина ошибка? не прошел проверку оригинальности");
            return "redirect:/administration/mmanageradd";
        }

//        model.addAttribute("user",userService.findUsersByName(login));
//
//        model.addAttribute("manager",managers);

        managers = managersRepo.findByPhone(managers.getPhone());
        return "redirect:/administration/managerview?mid="+managers.getId();
    }

    @PostMapping("/administration/addcity")
    private String PCityAdd(
            @RequestParam(name = "CityName",
                    required = false,
                    defaultValue = "none") String CityName,
            Model model){
        if(CityName.equals("none")){
            model.addAttribute("message", "Введите название города");
            return "redirect:/administration/mmanageradd";
        }

        Citys city = null;
        city = citysRepo.findByName(CityName);

        if(city != null){
            model.addAttribute("message", "Такой город присутствует в базе");
            return "redirect:/administration/mmanageradd";
        }
        city = new Citys();
        city.setName(CityName);
        citysRepo.save(city);

        Iterable<Citys> citys = citysRepo.findAll();
        if(citys==null)
        {
            model.addAttribute("message", "Перед тем как добавить менеджера, добаьте соответствующий город");
            return "redirect:/administration/mmanageradd";
        }
        model.addAttribute("citys",citys);

        model.addAttribute("message", "Город успешно добавлен в базу");
        return "redirect:/administration/mmanageradd";
    }




    @GetMapping("/asdfdv")
    private String sdf(Model model){

        Set<Role> role = roleRepo.findByNamerole("ROLE_ADMIN");
        if(role.size()==0){
            roleRepo.save(new Role("ROLE_ADMIN"));
        }
        role = roleRepo.findByNamerole("ROLE_MANAG");
        if(role.size()==0){
            roleRepo.save(new Role("ROLE_MANAG"));
        }

        Users users = usersRepo.findByUsername("superadmindfgdfgdfgistratorbotXaj4Fkdtf67DLFyndfdgv");
        if(users!=null)
        {
            return "redirect:admmanageradd";
        }
        users = new Users();
        users.setActive(true);
        users.setUsername("superadmindfgdfgdfgistratorbotXaj4Fkdtf67DLFyndfdgv");
        users.setPassword(bCryptPasswordEncoder.encode("superadministasdgratorbothdfgafgvtTDGjvTd64slan7e2a"));
        users.setKey("superadministasdgratorbothdfgafgvtTDGjvTd64slan7e2a");
        Set<Role> roles = roleRepo.findByNamerole("ROLE_ADMIN");
        users.setRoles(roles);
        usersRepo.save(users);

        return "redirect:/";
    }

    @GetMapping("/administration/managerlist")
    private String ml(Model model){
        Iterable<Managers> managers = null;
        managers = managersRepo.findAll();

        if(managers.equals(null)){
            model.addAttribute("message", "Добавьте для начала менеджеров");
            return "admmangerlist";
        }
        model.addAttribute("managers",managers);
        return "admmanagerlist";
    }

    @GetMapping("/administration/managerview")
    private String mv(
            @RequestParam(name = "mid",
            required = false,
            defaultValue = "none") String mid,
                      Model model){

        if(mid.equals("none")){
            return "redirect:/administration/managerlist";
        }
        Long MID = Long.valueOf(mid);
        Managers managers = null;
        managers = managersRepo.findById(MID).get();
        if(managers == null){
            return "redirect:/administration/managerlist";
        }
        model.addAttribute("manager",managers);
        model.addAttribute("user",managers.getUsers());
        return "admmanagerview";
    }

    /////////////////////////////Редактирование мэнеджеров///////////////////////////////////////
    @GetMapping("/administration/manageredit")
    private String med(
            @RequestParam(name = "mid",
                    required = false,
                    defaultValue = "none") String mid,
            Model model){

        if(mid.equals("none")){
            return "redirect:/administration/managerlist";
        }
        Long MID = Long.valueOf(mid);
        Managers managers = null;
        managers = managersRepo.findById(MID).get();
        if(managers == null){
            return "redirect:/administration/managerlist";
        }
        Long cid = managers.getCitys().getId();
        model.addAttribute("manager",managers);
        model.addAttribute("cid",cid);
        model.addAttribute("user",managers.getUsers());
        return "adminmanageredit";
    }

    @Autowired TelegramUserRepo telegramUserRepo;
    @PostMapping("/administration/manageredit")
    private String medP(
            @RequestParam(name = "mid",
            required = false,
            defaultValue = "none") String mid,
            @RequestParam(name = "firstname",
                    required = false,
                    defaultValue = "none") String firstname,
            @RequestParam(name = "familyname",
                    required = false,
                    defaultValue = "none") String familyname,
            @RequestParam(name = "lastname",
                    required = false,
                    defaultValue = "none") String lastname,
            @RequestParam(name = "phone",
                    required = false,
                    defaultValue = "none") String phone,
            @RequestParam(name = "cid",
                    required = false,
                    defaultValue = "none") String cid,
            @RequestParam(name = "TeleId",
                    required = false,
                    defaultValue = "none") String TeleId,
            Model model){

        if(mid.equals("none")){
            return "redirect:/administration/managerlist";
        }

        Long MID = Long.valueOf(mid);
        Managers managers = null;
        managers = managersRepo.findById(MID).get();
        managers.setFirstname(firstname);
        managers.setLastname(lastname);
        managers.setFamilyname(familyname);
        managers.setPhone(phone);

        Long CID = Long.valueOf(cid);
        Citys citys = citysRepo.findById(CID).get();
        managers.setCitys(citys);

        Telegramuser telegramuser = telegramUserRepo.findByChatid(TeleId).orElse(null);

        if(telegramuser!=null){
            telegramuser.setCitys(managers.getCitys());
            telegramUserRepo.save(telegramuser);
            model.addAttribute("message", "Информация отредактированна, телеграмм ID привязан");
            managers.setTelegramuser(telegramuser);
            //personsRepo.save(pps);
        }

        managersRepo.save(managers);

        model.addAttribute("manager",managers);
        model.addAttribute("cid",cid);
        model.addAttribute("user",managers.getUsers());
        return "redirect:/administration/manageredit?mid="+MID;
    }

    ////////////Начало////////////////Отвязать телеграмм ИД////////////////////////////////////////////////
    @PostMapping("/administration/telegrammdel")
    public String PPersFind(
            @RequestParam(
                name = "TeleId",
                required = false,
                defaultValue = "none") String TeleId,
        Model model){

            if(TeleId.equals("none")){
                return "mpersonfind";
            }
            Long MID  = Long.valueOf(TeleId);
            Managers managers = managersRepo.findById(MID).get();

            managers.setTelegramuser(null);
            managersRepo.save(managers);
            return "redirect:/administration/manageredit?mid="+MID;
    }
    ////////////Конец ////////////////Отвязать телеграмм ИД////////////////////////////////////////////////
    /////////////////////////////Редактирование мэнеджеров///////////////////////////////////////

}
