package com.example.masterhaus.controller;

import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.CategoryRepo;
import com.example.masterhaus.repos.PaymenthistorysRepo;
import com.example.masterhaus.repos.PersonsRepo;
import com.example.masterhaus.service.ManagerService;
import com.example.masterhaus.service.UserService;
import javassist.bytecode.stackmap.TypeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ManagerPersonBalanceController {
    @Autowired
    PersonsRepo personsRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    PaymenthistorysRepo paymenthistorysRepo;

    @Autowired
    ManagerService managerService;


    @Autowired
    UserService userService;
    @GetMapping("/manager/balanceupdate")
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

            if(!managerService.isPersonsManager(pps))
            {
                return "redirect:/manager/personfind";
            }
            if(pps!= null){
                Iterable<Categorys> ccat = categoryRepo.findAll();
                model.addAttribute("ccat", ccat);

                Iterable<Categorys> pi = pps.getCategorys();
                model.addAttribute("pi",pi);

                List<Paymenthistorys>  paymenthistorys = null;
                paymenthistorys = pps.getPaymenthistorys();
                if(paymenthistorys != null){
                    Collections.reverse(paymenthistorys);
                    model.addAttribute("paymenthistorys",paymenthistorys);
                }

                model.addAttribute("pps",pps);
                model.addAttribute("message","");
                return "balanceupdate";
            }
            else{
                model.addAttribute("message","Работник с таким номером телефона не найден");
            }

        }
        return "redirect:/manager/personfind";
    }

    @PostMapping("/manager/addsumm")
    private String PAddSumm(
            @RequestParam(name = "PersonId",
                    required = false,
                    defaultValue = "none") String PersonId,
            @RequestParam(name = "Balanceupdate",
                    required = false,
                    defaultValue = "none") String Balanceupdate,
            @RequestParam(name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model
    ){
        Phone=userService.getPhoneRegx(Phone);


        if(PersonId.equals("none"))
        {
            model.addAttribute("message", "Найдите человека которому хотите обновить баланс");
            return "redirect:/manager/balanceupdate?Phone="+Phone;
        }else
        if(Balanceupdate.equals("none")){
            model.addAttribute("message", "Вы не указали баланс зачисления поэтому все заново :Р ");
            return "redirect:/manager/balanceupdate?Phone="+Phone;
        }else {
            int balanceup = Integer.valueOf(Balanceupdate);
            Long personID = Long.valueOf(PersonId);
            //Зачисление средств на счет
            Persons persons = null;
            persons = personsRepo.findById(Long.valueOf(personID)).get();

            if(persons == null){
                return "redirect:/manager/mpersonfind";
            }

            if(!managerService.isPersonsManager(persons))
            {
                return "redirect:/manager/mpersonfind";
            }

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
            paymenthistorys.setPersons(persons);

            paymenthistorys = paymenthistorysRepo.save(paymenthistorys);
//            persons.getPaymenthistorys().add(paymenthistorys);
//            persons.setPaymenthistorys(paymenthistorys);


            persons.setCashaccounts(cashaccounts);

            List<Paymenthistorys> ph = null;
            ph = persons.getPaymenthistorys();
            if(ph.size()>15){
                Object paymenthistorys1 = ph.stream().skip(ph.size()-30).limit(30).toArray();
            }

            if(ph != null){

                Collections.reverse(ph);
                model.addAttribute("paymenthistorys",ph);
            }

            personsRepo.save(persons);
            model.addAttribute("pps",persons);
        }

        return "redirect:/manager/balanceupdate?Phone="+Phone;
    }

    @PostMapping("/manager/dellsumm")
    private String PDellSumm(
            @RequestParam(name = "PersonId",
                    required = false,
                    defaultValue = "none") String PersonId,
            @RequestParam(name = "Balanceupdate",
                    required = false,
                    defaultValue = "none") String Balanceupdate,
            @RequestParam(name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model
    ){
        Phone=userService.getPhoneRegx(Phone);


        Persons persons = null;
        int balancedown = 0;
        if(PersonId.equals("none"))
        {
            model.addAttribute("message", "Найдите человека которому хотите обновить баланс");
            return "redirect:/manager/balanceupdate?Phone="+Phone;
        }else
        if(Balanceupdate.equals("none")){
            model.addAttribute("message", "Вы не указали баланс зачисления поэтому все заново :Р ");
            return "redirect:/manager/balanceupdate?Phone="+Phone;
        }else {
            balancedown = Integer.valueOf(Balanceupdate);
            Long personID = Long.valueOf(PersonId);
            //Зачисление средств на счет

            persons = personsRepo.findById(Long.valueOf(personID)).get();

            if(persons == null){
                return "redirect:/manager/balanceupdate?Phone="+Phone;
            }

            if(!managerService.isPersonsManager(persons))
            {
                return "redirect:/manager/balanceupdate?Phone="+Phone;
            }

            Cashaccounts cashaccounts = null;
            cashaccounts = persons.getCashaccounts();
            if(cashaccounts == null)
            {
                model.addAttribute("message", "Списание не возможно, у работника отсутствует счет");
                return "redirect:/manager/balanceupdate?Phone="+Phone;
            }
            int cash = cashaccounts.getCash();

            if(cash<balancedown){
                model.addAttribute("message", "Сумма списания превышает баланс счета");
                List<Paymenthistorys> ph = null;
                ph = persons.getPaymenthistorys();
                if(ph.size()>15){
                    Object paymenthistorys1 = ph.stream().skip(ph.size()-30).limit(30).toArray();
                }

                if(ph != null){
                    Collections.reverse(ph);
                    model.addAttribute("paymenthistorys",ph);
                }
                return "redirect:/manager/balanceupdate?Phone="+Phone;
            }

            cash = cash-balancedown;
            cashaccounts.setCash(cash);

            Paymenthistorys paymenthistorys = new Paymenthistorys();
            paymenthistorys.setDowncash(balancedown);
            paymenthistorys.setOperation("down_panel_balance");
            paymenthistorys.setCash(cash);
            paymenthistorys.setPersons(persons);
            paymenthistorysRepo.save(paymenthistorys);
//            persons.getPaymenthistorys().add(paymenthistorys);
//            persons.getPaymenthistorys().add(paymenthistorys);

            List<Paymenthistorys> ph = null;
            ph = persons.getPaymenthistorys();
            if(ph.size()>15){
                Object paymenthistorys1 = ph.stream().skip(ph.size()-30).limit(30).toArray();
            }

            if(ph != null){
                Collections.reverse(ph);
                model.addAttribute("paymenthistorys",ph);
            }




            persons.setCashaccounts(cashaccounts);

            personsRepo.save(persons);
            model.addAttribute("pps",persons);
        }
        model.addAttribute("message", "C баланса счета списанна сумма: "+ balancedown+" рублей");
        return "redirect:/manager/balanceupdate?Phone="+Phone;
    }
}
