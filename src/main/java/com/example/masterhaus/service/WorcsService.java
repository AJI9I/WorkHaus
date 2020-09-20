package com.example.masterhaus.service;

import com.example.masterhaus.comparator.WorcsComparator;
import com.example.masterhaus.domain.Managers;
import com.example.masterhaus.domain.Persons;
import com.example.masterhaus.domain.Worcs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorcsService {

    @Autowired
    ManagerService managerService;
    public List<Worcs> getPersonsWorcs(Persons persons){
        Set<Worcs> worcs = null;
        worcs = persons.getWorcs();

        List<Worcs> worcsList = null;
        if(worcs!=null){
            worcsList = worcs.stream().sorted(new WorcsComparator()).collect(Collectors.toList());
            Collections.reverse(worcsList);
        }
        return worcsList;
    }

    public List<Worcs> getManagersWorcs(){
        Managers managers = managerService.getManagers();
        Set<Worcs> worcs = null;
        worcs = managers.getWorcs();

        List<Worcs> worcsList = null;
        if(worcs!=null){
            worcsList = worcs.stream().sorted(new WorcsComparator()).collect(Collectors.toList());
            Collections.reverse(worcsList);
        }
        return worcsList;
    }
}
