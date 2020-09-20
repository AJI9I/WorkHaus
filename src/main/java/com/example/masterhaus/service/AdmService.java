package com.example.masterhaus.service;

import com.example.masterhaus.domain.Managers;
import com.example.masterhaus.repos.ManagersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmService {
    @Autowired
    ManagersRepo managersRepo;

    public boolean addNewmanagers(Managers managers){
        Managers managers1 = managersRepo.findByPhone(managers.getPhone());
        if(managers1!=null){
            return false;
        }
        managers1 =managers;
        managersRepo.save(managers1);
        return true;
    }
}
