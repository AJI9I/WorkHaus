package com.example.masterhaus.controller;

import com.example.masterhaus.comparator.MessagefromsiteworcComparator;
import com.example.masterhaus.comparator.PersoninterviewrequestsComparator;
import com.example.masterhaus.domain.Citys;
import com.example.masterhaus.domain.Messagefromsiteworc;
import com.example.masterhaus.domain.Personinterviewrequest;
import com.example.masterhaus.repos.MessagefromsiteworcRepo;
import com.example.masterhaus.repos.PersoninterviewrequestRepo;
import com.example.masterhaus.service.ManagerService;

import com.example.masterhaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ManagermailController {

    @Autowired
    ManagerService managerService;

    @Autowired
    UserService userService;

    @GetMapping("/manager/messagesite")
    private String GBalUp(
            @RequestParam(name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model){

        Phone=userService.getPhoneRegx(Phone);


        List<Messagefromsiteworc> messagefromsiteworc =null;
        messagefromsiteworc =managerService.getManagers().getCitys().getMessagefromsiteworc();
        if(messagefromsiteworc.size() == 0){
            model.addAttribute("message","Новых сообщений с заявками не найденно");
            return "mmailsite";
        }
//        if(messagefromsiteworc.size()==1){
//            model.addAttribute("msgsw", messagefromsiteworc);
//            return "mmailsite";
//        }

        List<Messagefromsiteworc> messagefromsiteworcList =messagefromsiteworc
                .stream()
                .filter(p->p.isRelevant()==true)
                .sorted(new MessagefromsiteworcComparator())
                .collect(Collectors.toList());

        if(messagefromsiteworcList.size() == 0){
            model.addAttribute("message","Новых сообщений с заявками не найденно");
            return "mmailsite";
        }

        model.addAttribute("msgsw", messagefromsiteworcList);
        return "mmailsite";
    }
    @Autowired
    MessagefromsiteworcRepo messagefromsiteworcRepo;
    @PostMapping("/manager/messagesiteclose")
    private String msgc(
            @RequestParam(name = "mpid",
                    required = false,
                    defaultValue = "none") String mpid,
            Model model){
        if(mpid=="none"){
            return "redirect:/manager/messagesite";
        }
        Long MID = Long.valueOf(mpid);
            List<Messagefromsiteworc> messagefromsiteworc =null;
            messagefromsiteworc =managerService.getManagers().getCitys().getMessagefromsiteworc();
            if(messagefromsiteworc.size()==0){
            return "redirect:/manager/messagesite";
        }
        Messagefromsiteworc messagefromsiteworc1 =messagefromsiteworc.stream().filter(p->p.getId()==MID).findFirst().get();
        messagefromsiteworc1.setRelevant(false);
        messagefromsiteworcRepo.save(messagefromsiteworc1);
        return "redirect:/manager/messagesite";
    }

    @GetMapping("/manager/messagesiteperson")
    private String mspp(
            @RequestParam(name = "Phone",
                    required = false,
                    defaultValue = "none") String Phone,
            Model model){
        Phone=userService.getPhoneRegx(Phone);
        Set<Personinterviewrequest> personinterviewrequests = null;
        personinterviewrequests = managerService.getManagers().getCitys().getPersoninterviewrequest();
        if(personinterviewrequests.size()==0){
            model.addAttribute("message","Новых сообщений с заявками не найденно");
            return "messagesiteperson";
        }
        List<Personinterviewrequest> personinterviewrequestsList =personinterviewrequests
                .stream()
                .filter(p->p.isRelevant()==true)
                .sorted(new PersoninterviewrequestsComparator())
                .collect(Collectors.toList());

        if(personinterviewrequestsList.size() == 0){
            model.addAttribute("message","Новых сообщений с заявками не найденно");
            return "messagesiteperson";
        }
        model.addAttribute("msgsw", personinterviewrequestsList);
        return "messagesiteperson";
    }

    @Autowired
    PersoninterviewrequestRepo personinterviewrequestRepo;

    @PostMapping("/manager/messagesiteperson")
    private String msppc(
            @RequestParam(name = "mpid",
                    required = false,
                    defaultValue = "none") String mpid,
            Model model){

        if(mpid=="none"){
            return "redirect:/manager/messagesiteperson";
        }
        Long MID = Long.valueOf(mpid);
        Set<Personinterviewrequest> Personinterviewrequestlist =null;
        Personinterviewrequestlist =managerService.getManagers().getCitys().getPersoninterviewrequest();
        if(Personinterviewrequestlist.size()==0){
            return "redirect:/manager/messagesiteperson";
        }
        Personinterviewrequest personinterviewrequest =Personinterviewrequestlist.stream().filter(p->p.getId()==MID).findFirst().get();
        personinterviewrequest.setRelevant(false);
        personinterviewrequestRepo.save(personinterviewrequest);
        return "redirect:/manager/messagesiteperson";
    }

}
