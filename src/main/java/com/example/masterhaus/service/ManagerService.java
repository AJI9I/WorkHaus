package com.example.masterhaus.service;

import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.CashaccountsRepo;
import com.example.masterhaus.repos.ManagersRepo;
import com.example.masterhaus.repos.PaymenthistorysRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ManagerService {

    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        return users.getId();
    }


    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        return users.getUsername();
    }

    public Long getMangersId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        return users.getManagers().getId();
    }

    @Autowired
    ManagersRepo managersRepo;
    public Managers getManagers(){
        return managersRepo.findById(getMangersId()).get();
    }


    public String getFirstname() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        return users.getManagers().getFirstname();
    }


    public String getLastname() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        return users.getManagers().getLastname();
    }


    public String getFamilyname() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        return users.getManagers().getFamilyname();
    }


    public Long getCityId() {
        Long CityId = 1L;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        Citys citys =users.getManagers().getCitys();

        return citys.getId();
    }

    public boolean isPersonsManager(Persons persons){
        Long MID = getMangersId();
        Managers managers = persons.getManagers();
        if(managers == null){
            return false;
        }
        Long MPID = managers.getId();
        if (MPID.equals(MID)){
            return true;
        }
        return false;
    }
    public Set<Worcs> getPersonWorc(Persons persons){
        Set<Worcs> worcs = persons.getWorcs();
        return worcs;
    }

    @Autowired
    TelegramService telegramService;
    @Autowired
    CashaccountsRepo cashaccountsRepo;
    @Autowired
    PaymenthistorysRepo paymenthistorysRepo;

    public boolean returnByWorcPerson(Worcs worcs){
        Persons persons = worcs.getPersons();

        Cashaccounts cashaccounts = persons.getCashaccounts();
        Paymenthistorys paymenthistorys = new Paymenthistorys();

        double percent = worcs.getCategorys().getPercent();
        double price = worcs.getPrice();
        int p = telegramService.getPercent(price, percent);
        int cash = cashaccounts.getCash();
        cash = cash+p;
        cashaccounts.setCash(cash);
        cashaccountsRepo.save(cashaccounts);

        paymenthistorys.setCash(cash);
        paymenthistorys.setOperation("return_cash: " +worcs.getId());
        paymenthistorys.setUpcash(p);
        paymenthistorys.setPersons(persons);
        paymenthistorysRepo.save(paymenthistorys);
        return true;
    }

}
