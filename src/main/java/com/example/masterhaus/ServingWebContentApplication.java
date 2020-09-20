package com.example.masterhaus;

import com.example.masterhaus.bot.MyBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class ServingWebContentApplication {
    public static void main(String[] args){

        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "127.0.0.1");
        System.getProperties().put("socksProxyPort", "9150");

        ApiContextInitializer.init();



//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//
//        try {
//            telegramBotsApi.registerBot(new MyBot());
//        } catch (TelegramApiRequestException e) {
//            e.printStackTrace();
//        }

        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
