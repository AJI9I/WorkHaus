package com.example.masterhaus.controller;

import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.AreasRepo;
import com.example.masterhaus.repos.CitysRepo;
import com.example.masterhaus.repos.ManagersRepo;
import com.example.masterhaus.repos.SuburbsRepo;
import com.example.masterhaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuperadminController {

    @Autowired
    ManagersRepo managersRepo;

    @Autowired
    CitysRepo citysRepo;

    @Autowired
    AreasRepo areasRepo;

    @Autowired
    SuburbsRepo suburbsRepo;

    @GetMapping("/manageradd")
    private String GManagerAdd(Model model){

        Iterable<Citys> citys = null;
        citys = citysRepo.findAll();
        model.addAttribute("citys", citys);
        return "manageradd";
    }

    @Autowired
    UserService userService;

    @PostMapping("/manageradd")
    private String PManagerAdd(
            @RequestParam(name = "Family",
                    required = false,
                    defaultValue = "none") String Family,
            @RequestParam(name = "Name",
                    required = false,
                    defaultValue = "none") String Name,
            @RequestParam(name = "Surname",
                    required = false,
                    defaultValue = "none") String Surname,
            @RequestParam(name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        if(Family.equals("none")||Name.equals("none")||Surname.equals("none")||Phone.equals("none")){

            model.addAttribute("message", "Заполните все поля должным образом");
            return "manageradd";
        }
        Managers managers = null;
        managers = managersRepo.findByPhone(Phone);
        if(managers!=null){

            model.addAttribute("message", "Такой менеджер уже присутствует в базе");
            return "manageradd";
        }
        managers = new Managers();
        managers.setFirstname(Name);
        managers.setFamilyname(Family);
        managers.setLastname(Surname);
        managers.setPhone(Phone);
        managersRepo.save(managers);
        model.addAttribute("message", "Менеджер успешно добавлен в базу");
        return "manageradd";
    }


    @PostMapping("/addcity")
    private String PCityAdd(
            @RequestParam(name = "CityName",
                    required = false,
                    defaultValue = "none") String CityName,
            Model model){
        if(CityName.equals("none")){
            model.addAttribute("message", "Введите название города");
            return "mmanageradd";
        }

        Citys city = null;
        city = citysRepo.findByName(CityName);

        if(city != null){
            model.addAttribute("message", "Такой город присутствует в базе");
            return "mmanageradd";
        }
        city = new Citys();
        city.setName(CityName);
        citysRepo.save(city);
        model.addAttribute("message", "Город успешно добавлен в базу");
        return "redirect:mmanageradd";
    }

    @PostMapping("/addarea")
    private String PAreaAdd(
            @RequestParam(name = "CityName",
                    required = false,
                    defaultValue = "none") String CityName,
            @RequestParam(name = "AreaName",
                    required = false,
                    defaultValue = "none") String AreaName,
            Model model){
        if(CityName.equals("none")||AreaName.equals("none")){
            model.addAttribute("message", "Заполните форму всоответсвии со здравым смыслом");
            return "redirect:manageradd";
        }

        Citys city = null;
        city = citysRepo.findById(Long.valueOf(CityName)).get();

        if(city == null){
            model.addAttribute("message", "Такого города нет в базе данных");
            return "redirect:manageradd";
        }
        Areas areas = null;
        areas = areasRepo.findByAreasname(AreaName);
        if(areas!=null){
            model.addAttribute("message", "Такой район присутствует в базе данных");
            return "redirect:manageradd";
        }
        areas = new Areas();
        areas.setAreasname(AreaName);
        city.getAreas().add(areas);
        citysRepo.save(city);

        model.addAttribute("message", "Район успешно добавлен в базу " + AreaName);
        return "redirect:manageradd";
    }


    @PostMapping("/addsuburbs")
    private String PSuburbsAdd(
            @RequestParam(name = "CityName",
                    required = false,
                    defaultValue = "none") String CityName,
            @RequestParam(name = "SuburbsName",
                    required = false,
                    defaultValue = "none") String SuburbsName,
            Model model){
        if(CityName.equals("none")||SuburbsName.equals("none")){
            model.addAttribute("message", "Заполните форму всоответсвии со здравым смыслом");
            return "redirect:manageradd";
        }

        Citys city = null;
        city = citysRepo.findById(Long.valueOf(CityName)).get();

        if(city == null){
            model.addAttribute("message", "Такого города нет в базе данных");
            return "redirect:manageradd";
        }
        Suburbs suburbs = null;
        suburbs = suburbsRepo.findByName(SuburbsName);
        if(suburbs!=null){
            model.addAttribute("message", "Такой район присутствует в базе данных");
            return "redirect:manageradd";
        }
        suburbs = new Suburbs();
        suburbs.setName(SuburbsName);
        city.getSuburbs().add(suburbs);
        citysRepo.save(city);

        model.addAttribute("message", "Район успешно добавлен в базу " + SuburbsName);
        return "redirect:manageradd";
    }

}
