package com.example.masterhaus.controller;

import com.example.masterhaus.domain.Citys;
import com.example.masterhaus.domain.dto.CaptchaResponseDto;
import com.example.masterhaus.repos.CitysRepo;
import com.example.masterhaus.service.MessageService;
import com.example.masterhaus.service.TelegramService;
import com.example.masterhaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.nio.file.LinkOption;
import java.util.Collections;
import java.util.List;

@Controller
public class GeneralaccesspathController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";


    @Autowired
    CitysRepo citysRepo;

    @Autowired
    MessageService messageService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/requestcustomer")
    public String GAP(Model model){
        List<Citys> citys = (List<Citys>) citysRepo.findAll();
        model.addAttribute("citys", citys);
        return "requestcustomer";
    }

    @PostMapping("/requestcustomer")
    public String request(@RequestParam(name = "name",
            required = false,
            defaultValue = "none") String name,
                          @RequestParam(name = "cid",
                                  required = false,
                                  defaultValue = "none") String cid,
                          @RequestParam(name = "phone",
                                  required = false,
                                  defaultValue = "none") String phone,
                          @RequestParam(name = "message",
                                  required = false,
                                  defaultValue = "none") String message,
                          @RequestParam("g-recaptcha-response") String captchaResponse,
                          Model model){
        List<Citys> cityss = (List<Citys>) citysRepo.findAll();
        model.addAttribute("citys", cityss);

        if(name.equals("none") || phone.equals("none")||message.equals("none")||cid.equals("none")){
            model.addAttribute("message", "Заполните форму в соответсвии со здравым смыслом");
            return "indexRequestCustomer";
        }
        phone = phone.replace("/","")
                .replace("'","")
                .replace("\"","")
                .replace("=","")
                .replace("\\","")
                .replace("(","")
                .replace(")","")
                .replace(" ","")
                .replace("+","");
        name = name.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");
        message = message.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");
        cid = cid.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");

        //        Методы капча

        String url =String.format(CAPTCHA_URL,secret,captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if(!response.isSuccess()){
            model.addAttribute("message", "Вы не отметили капчу");
            return "indexRequestCustomer";
        }

        Long CID = Long.valueOf(cid);
        Citys citys = null;
        citys = citysRepo.findById(CID).get();

        if(citys == null){
            model.addAttribute("message", "Укажите правильный город");
            return "indexRequestCustomer";
        }
        messageService.setNewMessage(message,phone,name,citys,true);

        telegramService.sendMessageManager(message,phone,name,citys);

        model.addAttribute("message", "Заявка успешно отправленна, после ее обработки с Вами свяжется специалист");
        return "indexRequestCustomer";
    }

    @GetMapping("/personworcinterview")
    public String GPP(Model model){
        List<Citys> citys = (List<Citys>) citysRepo.findAll();
        model.addAttribute("citys", citys);
        return "indexRequestPerson";
    }

    @PostMapping("/personworcinterview")
    public String requestInterview(@RequestParam(name = "name",
            required = false,
            defaultValue = "none") String name,
                          @RequestParam(name = "cid",
                                  required = false,
                                  defaultValue = "none") String cid,
                          @RequestParam(name = "phone",
                                  required = false,
                                  defaultValue = "none") String phone,
                          @RequestParam(name = "message",
                                  required = false,
                                  defaultValue = "none") String message,
                                   @RequestParam("g-recaptcha-response") String captchaResponse,
                          Model model){
        List<Citys> cityss = (List<Citys>) citysRepo.findAll();
        model.addAttribute("citys", cityss);

        if(name.equals("none") || phone.equals("none")||message.equals("none")||cid.equals("none")){
            model.addAttribute("message", "Заполните форму в соответсвии со здравым смыслом");
            return "indexRequestPerson";
        }
        phone = phone.replace("/","")
                .replace("'","")
                .replace("\"","")
                .replace("=","")
                .replace("\\","")
                .replace("(","")
                .replace(")","")
                .replace(" ","")
                .replace("+","");
        name = name.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");
        message = message.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");
        cid = cid.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");

        Long CID = Long.valueOf(cid);
        Citys citys = null;
        citys = citysRepo.findById(CID).get();

        if(citys == null){
            model.addAttribute("message", "Укажите правильный город");
            return "indexRequestPerson";
        }

        //        Методы капча

        String url =String.format(CAPTCHA_URL,secret,captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if(!response.isSuccess()){
            model.addAttribute("message", "Вы не отметили капчу");
            return "indexRequestPerson";
        }


        messageService.setNewMessagePersonInterview(message,phone,name,citys);

        telegramService.sendMessageManager(message,phone,name,citys);

        model.addAttribute("message", "Анкета успешно отправленна, после ее обработки мы с Вами свяжемся");
        return "indexRequestPerson";
    }

    @GetMapping("/miass")
    public String GPM(Model model){

        return "indexMiass";
    }

    //////////////////запрос с миасса/////////////////////
    @Autowired
    UserService userService;

    @Autowired
    TelegramService telegramService;

    @PostMapping("/rquestmiass")
    public String requestM(@RequestParam(name = "name",
            required = false,
            defaultValue = "none") String name,
                          @RequestParam(name = "phone",
                                  required = false,
                                  defaultValue = "none") String phone,
                          @RequestParam(name = "message",
                                  required = false,
                                  defaultValue = "none") String message,
                          @RequestParam("g-recaptcha-response") String captchaResponse,
                          Model model){
        List<Citys> cityss = (List<Citys>) citysRepo.findAll();
        model.addAttribute("citys", cityss);

        if(name.equals("none") || phone.equals("none")||message.equals("none")){
            model.addAttribute("message", "Заполните форму в соответсвии со здравым смыслом");
            return "indexRequestCustomer";
        }
        phone = phone.replace("/","")
                .replace("'","")
                .replace("\"","")
                .replace("=","")
                .replace("\\","")
                .replace("(","")
                .replace(")","")
                .replace(" ","")
                .replace("+","");

        String Phone=userService.getPhoneRegx(phone);


        name = name.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");
        message = message.replace("/","").replace("'","").replace("\"","").replace("=","").replace("\\","");

        //        Методы капча

        String url =String.format(CAPTCHA_URL,secret,captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if(!response.isSuccess()){
            model.addAttribute("message", "Вы не отметили капчу");
            return "indexRequestCustomer";
        }

        Long CID = citysRepo.findByName("Миасс").getId();
        Citys citys = null;
        citys = citysRepo.findById(CID).get();

        if(citys == null){
            model.addAttribute("message", "Укажите правильный город");
            return "indexRequestCustomer";
        }
        //messageService.setNewMessage(message,Phone,name,citys,true);

        telegramService.sendMessageManager(message,Phone,name,citys);

        model.addAttribute("message", "Заявка успешно отправленна, после ее обработки с Вами свяжется специалист");
        return "redirect:/miass";
    }
    //////////////////запрос с миасса/////////////////////

    ////////////////// Франшиза /////////////////////
    @GetMapping("/sale")
    public String sale(Model model){

        return "sale";
    }
    ////////////////// Франшиза /////////////////////

}
