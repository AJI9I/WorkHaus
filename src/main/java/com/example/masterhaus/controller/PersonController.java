package com.example.masterhaus.controller;

import com.example.masterhaus.domain.Categorys;
import com.example.masterhaus.domain.Persons;
import com.example.masterhaus.repos.CategoryRepo;
import com.example.masterhaus.repos.PersonsRepo;
import com.example.masterhaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PersonController {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    PersonsRepo personsRepo;




    @GetMapping("/personadd")
    private String GPersAdd(Model model){
        Iterable<Categorys> cat = categoryRepo.findAll();
        model.addAttribute("cat",cat);
    return "personadd";
    }

    @Autowired
    UserService userService;

    @PostMapping("/personadd")
    public String PPersAdd(
            @RequestParam String Name,
            @RequestParam String Family,
            @RequestParam String Surname,
            @RequestParam String Phone,
            Model model
    ){

        Phone=userService.getPhoneRegx(Phone);

        Persons pps = personsRepo.findByPhone(Phone);
        if( pps == null){
            Persons psons = new Persons(Name,Surname,Family,Phone);
            personsRepo.save(psons);
            model.addAttribute("message", "Работник успешно добавленн");
        }
        else {
            model.addAttribute("message", "Работник с таким номером телефона присутствует");
        }

        return "personadd";
    }

    @GetMapping("/personfind")
    public String GPersFind(
            @RequestParam(
                    name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        Persons pps = null;
        if(Phone.equals("none")){
            model.addAttribute("message","Для поиска работника введите номер телефона");
        }
        else{

            pps = personsRepo.findByPhone(Phone);

            if(pps!= null){
                Iterable<Categorys> ccat = categoryRepo.findAll();
                Iterable<Categorys> pi = pps.getCategorys();
                model.addAttribute("pi",pi);

                model.addAttribute("ccat", ccat);
                model.addAttribute("pps",pps);
                model.addAttribute("message","Для поиска работника введите номер телефона");
            }
            else{
                model.addAttribute("message","Работник с таким номером телефона не найден");
            }


        }
//        model.addAttribute("pps",pps);
        return "personfind";
    }

    @PostMapping("/personfind")
    public String PPersFind(
            @RequestParam String PersonId,
            @RequestParam String Name,
            @RequestParam String Family,
            @RequestParam String Surname,
            @RequestParam String Phone,
            @RequestParam String TeleId,
            Model model){

        Phone=userService.getPhoneRegx(Phone);

        Iterable<Categorys> ccat = categoryRepo.findAll();
        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();
        pps.setFamily(Family);
        pps.setName(Name);
        pps.setSurname(Surname);
        pps.setPhone(Phone);
        personsRepo.save(pps);

        Iterable<Categorys> pi = pps.getCategorys();
        model.addAttribute("pi",pi);

        model.addAttribute("message","Информация отредактированна");

        model.addAttribute("ccat", ccat);
        model.addAttribute("pps",pps);

        return "personfind";
    }

    @PostMapping("/addcategoryperson")
    public String addCatPers(
                    @RequestParam String CategoryId,
                    @RequestParam String PersonId,
                    Model model
                             ){

        Persons pp = personsRepo.findById(Long.valueOf(PersonId)).get();
        Categorys cc = categoryRepo.findById(Long.valueOf(CategoryId)).get();

        pp.getCategorys().add(cc);
        personsRepo.save(pp);

        Iterable<Categorys> pi = personsRepo.findById(Long.valueOf(PersonId)).get().getCategorys();
        model.addAttribute("pi",pi);

        Iterable<Categorys> ccat = categoryRepo.findAll();

        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();


        model.addAttribute("ccat", ccat);
        model.addAttribute("pps",pps);

        return "personfind";
    }

    @PostMapping("/dellcategoryperson")
    public String DelCatPers(
            @RequestParam String CategoryId,
            @RequestParam String PersonId,
            Model model
    ){
        Persons pp = personsRepo.findById(Long.valueOf(PersonId)).get();
        Categorys cc = categoryRepo.findById(Long.valueOf(CategoryId)).get();

        pp.getCategorys().remove(cc);
        personsRepo.save(pp);

        Iterable<Categorys> pi = personsRepo.findById(Long.valueOf(PersonId)).get().getCategorys();
        model.addAttribute("pi",pi);



        Iterable<Categorys> ccat = categoryRepo.findAll();
        Persons pps = personsRepo.findById(Long.valueOf(PersonId)).get();
        model.addAttribute("ccat", ccat);
        model.addAttribute("pps",pps);
        return "personfind";
    }

}
