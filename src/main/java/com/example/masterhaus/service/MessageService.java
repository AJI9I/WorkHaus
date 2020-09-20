package com.example.masterhaus.service;

import com.example.masterhaus.domain.Citys;
import com.example.masterhaus.domain.Messagefromsiteworc;
import com.example.masterhaus.domain.Personinterviewrequest;
import com.example.masterhaus.repos.MessagefromsiteworcRepo;
import com.example.masterhaus.repos.PersoninterviewrequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {


    @Autowired
    MessagefromsiteworcRepo messagefromsiteworcRepo;

    @Autowired
    PersoninterviewrequestRepo personinterviewrequestRepo;

    public void setNewMessage(String message, String phone, String name, Citys citys, boolean relevant){

        Messagefromsiteworc messagefromsiteworc = new Messagefromsiteworc(message, phone,name,citys,relevant);
        messagefromsiteworcRepo.save(messagefromsiteworc);

    }

    public void setNewMessagePersonInterview(String message, String phone, String name, Citys citys){

        Personinterviewrequest personinterviewrequest = new Personinterviewrequest(message, phone,name,citys,true);
        personinterviewrequestRepo.save(personinterviewrequest);

    }
}
