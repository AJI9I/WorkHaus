package com.example.masterhaus.service;

import com.example.masterhaus.bot.MyBot;
import com.example.masterhaus.domain.Categorys;
import com.example.masterhaus.domain.Managers;
import com.example.masterhaus.domain.Persons;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TelegramMessageService extends MyBot {


    @Override
    protected void SendMSG() {
        SendMessage message = new SendMessage();
        message.setText("text");
        message.setParseMode("HTML");
        message.setChatId(Long.parseLong("316464237"));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void SendPersonInformation(Managers managers, Categorys categorys){

        Set<Persons> persons = managers.getPersons();
        Long CID = categorys.getId();

        List<Persons> personsList = categorys.getPersons().stream().filter(p->p.getManagers().getId()==managers.getId()).collect(Collectors.toList());
        for (Persons p: personsList
             ) {
            if(p.getTelegramuser()!=null){
                send(p.getTelegramuser().getChatid());
            }

        }
    }

    @Async
    public void send(String chatId){
        SendMessage message = new SendMessage();
        message.setText("Для Вас имеется новая работа, запросите список указанных Вами категорий работ кнопкой клавиатуры 'Мои категории заявок'");
        message.setParseMode("HTML");
        message.setChatId(Long.parseLong(chatId));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendManager(String chatId, String mes, String phone, String name){
        SendMessage message = new SendMessage();
        String mess = String.format("Новая заявка от: <b>" + name +"</b>\n"+
                "Номер телефона: <b>" + phone+"</b>\n"+
                "Сообщение: \n<b>" + mes+"</b>");
        message.setText(mess);
        message.setParseMode("HTML");
        message.setChatId(Long.parseLong(chatId));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendMess(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
