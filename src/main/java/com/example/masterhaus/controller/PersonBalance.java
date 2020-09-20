package com.example.masterhaus.controller;

import com.example.masterhaus.domain.Cashaccounts;
import com.example.masterhaus.domain.Categorys;
import com.example.masterhaus.domain.Paymenthistorys;
import com.example.masterhaus.domain.Persons;
import com.example.masterhaus.repos.CategoryRepo;
import com.example.masterhaus.repos.PaymenthistorysRepo;
import com.example.masterhaus.repos.PersonsRepo;
import com.example.masterhaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class PersonBalance {

    @Autowired
    PersonsRepo personsRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    PaymenthistorysRepo paymenthistorysRepo;


    @Autowired
    UserService userService;
    @GetMapping("/balanceupdate")
    private String GBalUp(
            @RequestParam(name = "Phone",
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
        return "balanceupdate";
    }

    @PostMapping("/addsumm")
    private String PAddSumm(
            @RequestParam(name = "PersonId",
                    required = false,
                    defaultValue = "none") String PersonId,
            @RequestParam(name = "Balanceupdate",
                    required = false,
                    defaultValue = "none") String Balanceupdate,
            Model model
    ){
        if(PersonId.equals("none"))
        {
            model.addAttribute("message", "Найдите человека которому хотите обновить баланс");
            return "balanceupdate";
        }else
        if(Balanceupdate.equals("none")){
            model.addAttribute("message", "Вы не указали баланс зачисления поэтому все заново :Р ");
            return "balanceupdate";
        }else {
            int balanceup = Integer.valueOf(Balanceupdate);
            Long personID = Long.valueOf(PersonId);
            //Зачисление средств на счет
            Persons persons = null;
            persons = personsRepo.findById(Long.valueOf(personID)).get();

            Cashaccounts cashaccounts = null;
            cashaccounts = persons.getCashaccounts();
            if(cashaccounts == null)
            {
                cashaccounts = new Cashaccounts();
                persons.setCashaccounts(cashaccounts);
                personsRepo.save(persons);
            }
            Paymenthistorys paymenthistorys = new Paymenthistorys();
            paymenthistorys.setUpcash(balanceup);
            paymenthistorys.setOperation("up_panel_balance");


            int cash = cashaccounts.getCash();
            cash = cash+balanceup;
            cashaccounts.setCash(cash);

            paymenthistorys.setCash(cash);

            paymenthistorys = paymenthistorysRepo.save(paymenthistorys);
            persons.getPaymenthistorys().add(paymenthistorys);
//            persons.setPaymenthistorys(paymenthistorys);

            persons.setCashaccounts(cashaccounts);

            personsRepo.save(persons);
        }

        return "redirect:balanceupdate";
    }

    @PostMapping("/dellsumm")
    private String PDellSumm(
            @RequestParam(name = "PersonId",
                    required = false,
                    defaultValue = "none") String PersonId,
            @RequestParam(name = "Balanceupdate",
                    required = false,
                    defaultValue = "none") String Balanceupdate,
            Model model
    ){
        Persons persons = null;
        int balancedown = 0;
        if(PersonId.equals("none"))
        {
            model.addAttribute("message", "Найдите человека которому хотите обновить баланс");
            return "balanceupdate";
        }else
        if(Balanceupdate.equals("none")){
            model.addAttribute("message", "Вы не указали баланс зачисления поэтому все заново :Р ");
            return "balanceupdate";
        }else {
            balancedown = Integer.valueOf(Balanceupdate);
            Long personID = Long.valueOf(PersonId);
            //Зачисление средств на счет

            persons = personsRepo.findById(Long.valueOf(personID)).get();

            Cashaccounts cashaccounts = null;
            cashaccounts = persons.getCashaccounts();
            if(cashaccounts == null)
            {
                model.addAttribute("message", "Списание не возможно, у работника отсутствует счет");
               return "redirect:balanceupdate";
            }
            int cash = cashaccounts.getCash();

            if(cash<balancedown){
                model.addAttribute("message", "Сумма списания превышает баланс счета");
                return "redirect:balanceupdate";
            }

            cash = cash-balancedown;
            cashaccounts.setCash(cash);

            Paymenthistorys paymenthistorys = new Paymenthistorys();
            paymenthistorys.setDowncash(balancedown);
            paymenthistorys.setOperation("down_panel_balance");
            paymenthistorys.setCash(cash);
            persons.getPaymenthistorys().add(paymenthistorys);




            persons.setCashaccounts(cashaccounts);

            personsRepo.save(persons);
        }
        model.addAttribute("message", "C баланса счета списанна сумма: "+ balancedown+" "+ persons.getName()+" " +persons.getFamily()+" "+persons.getSurname()+" ");
        return "redirect:balanceupdate";
    }

}
