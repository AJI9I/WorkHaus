package com.example.masterhaus.controller;

import com.example.masterhaus.domain.Areas;
import com.example.masterhaus.domain.Categorys;
import com.example.masterhaus.domain.Citys;
import com.example.masterhaus.domain.Suburbs;
import com.example.masterhaus.repos.AreasRepo;
import com.example.masterhaus.repos.CategoryRepo;
import com.example.masterhaus.repos.CitysRepo;
import com.example.masterhaus.repos.SuburbsRepo;
import com.example.masterhaus.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    CitysRepo citysRepo;

    @Autowired
    AreasRepo areasRepo;

    @Autowired
    ManagerService managerService;

    @Autowired
    SuburbsRepo suburbsRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/manager/addareaandsuburbs")
    private String GManagerAdd(Model model){

        model.addAttribute("musername",managerService.getUsername());

        Citys cyt = citysRepo.findById(managerService.getCityId()).get();

        List<Suburbs> suburbs = cyt.getSuburbs();
        model.addAttribute("suburbs",suburbs);

        List<Areas> areas = cyt.getAreas();
        model.addAttribute("areas", areas);


        model.addAttribute("citys", cyt);
        return "mareaandsuburbsadd";
    }


    //Добавления района
    @PostMapping("/manager/addarea")
    private String PAreaAdd(
            @RequestParam(name = "CityName",
                    required = false,
                    defaultValue = "none") String CityName,
            @RequestParam(name = "AreaName",
                    required = false,
                    defaultValue = "none") String AreaName,
            Model model){
        model.addAttribute("musername",managerService.getUsername());
        if(CityName.equals("none")||AreaName.equals("none")){
            model.addAttribute("message", "Заполните форму всоответсвии со здравым смыслом");
            return "redirect:/manager/addareaandsuburbs";
        }

        Citys city = null;
        city = citysRepo.findById(Long.valueOf(CityName)).get();

        if(city == null){
            model.addAttribute("message", "Такого города нет в базе данных");
            return "redirect:/manager/addareaandsuburbs";
        }
        Areas areas = null;
        areas = areasRepo.findByAreasname(AreaName);
        if(areas!=null){
            model.addAttribute("message", "Такой район присутствует в базе данных");
            return "redirect:/manager/addareaandsuburbs";
        }
        areas = new Areas();
        areas.setAreasname(AreaName);
        city.getAreas().add(areas);
        citysRepo.save(city);

        model.addAttribute("message", "Район успешно добавлен в базу " + AreaName);
        return "redirect:/manager/addareaandsuburbs";
    }

    @PostMapping("/manager/addsuburbs")
    private String PSuburbsAdd(
            @RequestParam(name = "CityName",
                    required = false,
                    defaultValue = "none") String CityName,
            @RequestParam(name = "SuburbsName",
                    required = false,
                    defaultValue = "none") String SuburbsName,
            Model model){
        model.addAttribute("musername",managerService.getUsername());
        if(CityName.equals("none")||SuburbsName.equals("none")){
            model.addAttribute("message", "Заполните форму всоответсвии со здравым смыслом");
            return "redirect:/manager/addareaandsuburbs";
        }

        Citys city = null;
        city = citysRepo.findById(Long.valueOf(CityName)).get();

        if(city == null){
            model.addAttribute("message", "Такого города нет в базе данных");
            return "redirect:/manager/addareaandsuburbs";
        }
        Suburbs suburbs = null;
        suburbs = suburbsRepo.findByName(SuburbsName);
        if(suburbs!=null){
            model.addAttribute("message", "Такой район присутствует в базе данных");
            return "redirect:/manager/addareaandsuburbs";
        }
        suburbs = new Suburbs();
        suburbs.setName(SuburbsName);
        city.getSuburbs().add(suburbs);
        citysRepo.save(city);

        model.addAttribute("message", "Район успешно добавлен в базу " + SuburbsName);
        return "redirect:/manager/addareaandsuburbs";
    }

    @GetMapping("/manager/categoryadd")
    public String gg(Model model){
        model.addAttribute("musername",managerService.getUsername());
        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        return "mcategoryadd";
    }

    @PostMapping("/manager/categoryadd")
    public String add(@RequestParam String CategoryFather,
                      @RequestParam String CategoryName,
                      @RequestParam String CategoryPercent,
                      @RequestParam String CategoryDescription,
                      Model model){

        model.addAttribute("musername",managerService.getUsername());
        Categorys cacc = categoryRepo.findByName(CategoryName);
        if(cacc == null){
            Categorys catadd = new Categorys(CategoryFather, CategoryName, CategoryPercent, CategoryDescription);
            categoryRepo.save(catadd);
            model.addAttribute("caid",catadd);

            model.addAttribute("message", "Категория успешно добавленна");
        }
        else {
            model.addAttribute("message", "Категория уже присутствует");
        }

        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        return "mcategoryadd";
    }

    @GetMapping("/manager/categoryedit")
    public String Gedit(
            @RequestParam(name="CategoryId") String id,
            Model model){

        model.addAttribute("musername",managerService.getUsername());

        Categorys caid = categoryRepo.findById(Long.valueOf(id)).get();
        model.addAttribute("caid",caid);

        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);

        return "mcategoryedit";
    }

    @PostMapping("/manager/categoryedit")
    private String Pedit(
            @RequestParam(name = "CategoryFather",
                    required = false,
                    defaultValue = "none") String CategoryFather,
            @RequestParam(name = "CategoryName",
                    required = false,
                    defaultValue = "none") String CategoryName,
            @RequestParam(name = "CategoryPercent",
                    required = false,
                    defaultValue = "none") String CategoryPercent,
            @RequestParam(name = "CategoryDescription",
                    required = false,
                    defaultValue = "none") String CategoryDescription,
            @RequestParam(name = "Id",
                    required = false,
                    defaultValue = "none") String Id,
            Model model
//            HttpServletRequest req
    ){

        if(CategoryName.equals("none")||CategoryPercent.equals("none")||Id.equals("none")){
            return "redirect:/manager/categoryedit?CategoryId="+Id;
        }

        model.addAttribute("musername",managerService.getUsername());
        Categorys ccc = categoryRepo.findById(Long.valueOf(Id)).get();

        int lvl =1;
        if(CategoryFather.equals("none")){
            CategoryFather = "0";
            lvl =0;
        }


        if(ccc!= null)
        {
            ccc.setPercent(Double.valueOf(CategoryPercent));
            ccc.setFather(Integer.valueOf(CategoryFather));
            ccc.setLvl(lvl);
            ccc.setName(CategoryName);
            ccc.setDiscription(CategoryDescription);
            categoryRepo.save(ccc);
            model.addAttribute("caid",ccc);
            model.addAttribute("message", "Обновление прошло успешно");
        }
        else {

            model.addAttribute("message", "Заполнение всех полей обязательно");

        }
        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        return "mcategoryedit";
    }

}
