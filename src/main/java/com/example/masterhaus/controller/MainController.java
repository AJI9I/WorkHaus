package com.example.masterhaus.controller;

import com.example.masterhaus.domain.Categorys;
import com.example.masterhaus.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private CategoryRepo categoryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/")
    private String main(Model model){

        return "indexGreeting";
    }

    @GetMapping("/categoryadd")
    public String gg(Model model){
        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        return "categoryadd";
    }

    @PostMapping("/categoryadd")
    public String add(@RequestParam String CategoryFather,
               @RequestParam String CategoryName,
               @RequestParam String CategoryPercent,
               @RequestParam String CategoryDescription,
               Model model){


        Categorys cacc = categoryRepo.findByName(CategoryName);
        if(cacc == null){
            Categorys catadd = new Categorys(CategoryFather, CategoryName, CategoryPercent, CategoryDescription);
            categoryRepo.save(catadd);
            model.addAttribute("message", "Категория успешно добавленна");
        }
        else {
            model.addAttribute("message", "Категория уже присутствует");
        }


        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        return "categoryadd";
    }

    @GetMapping("/categoryedit")
    public String Gedit(
            @RequestParam(name="CategoryId") String id,
            Model model){
        Categorys caid = categoryRepo.findById(Long.valueOf(id)).get();

        Iterable<Categorys> cat = categoryRepo.findAll();

        model.addAttribute("caid",caid);
        model.addAttribute("cat",cat);

        return "categoryedit";
    }

    @PostMapping("/categoryedit")
    private String Pedit(
            @RequestParam String CategoryFather,
            @RequestParam String CategoryName,
            @RequestParam String CategoryPercent,
            @RequestParam String CategoryDescription,
            @RequestParam String Id,
            Model model
//            HttpServletRequest req
    ){

        Categorys ccc = categoryRepo.findById(Long.valueOf(Id)).get();

        if(ccc!= null)
        {
            ccc.setPercent(Double.valueOf(CategoryPercent));
            ccc.setFather(Integer.valueOf(CategoryFather));
            ccc.setName(CategoryName);
            ccc.setDiscription(CategoryDescription);
            categoryRepo.save(ccc);
            model.addAttribute("message", "Обновление прошло успешно");
        }
        else {

            model.addAttribute("message", "Заполнение всех полей обязательно");

        }
        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
        return "categoryadd";
    }


}
