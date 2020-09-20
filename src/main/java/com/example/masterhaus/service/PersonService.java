package com.example.masterhaus.service;

import com.example.masterhaus.comparator.PersonsComparator;
import com.example.masterhaus.domain.Managers;
import com.example.masterhaus.domain.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    ManagerService managerService;

    public List<Persons> getLast10Persons(){
        Managers managers = managerService.getManagers();
        Set<Persons> persons = null;
        persons = managers.getPersons();
        List<Persons> personsList = null;
        if(persons!=null){
            personsList = persons.stream().sorted(new PersonsComparator()).collect(Collectors.toList());
            Collections.reverse(personsList);
            if(persons.size()>10){
                personsList = personsList.stream().limit(10).collect(Collectors.toList());
            }

        }
        return personsList;
    }
}
