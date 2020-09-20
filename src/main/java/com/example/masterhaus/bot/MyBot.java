package com.example.masterhaus.bot;

import com.example.masterhaus.domain.*;
import com.example.masterhaus.repos.WorcRepo;
import com.example.masterhaus.service.TelegramMessageService;
import com.example.masterhaus.service.TelegramService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
//public class MyBot  extends TelegramLongPollingBot implements ApplicationContextAware {
public abstract class MyBot  extends TelegramLongPollingBot {

    @Autowired
    private TelegramService telegramService;

//    private final TelegramService telegramService;
//
//    private MyBot(TelegramService telegramService){
//        this.telegramService =telegramService;
//    }

//    private static ApplicationContext ctx;
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        MyBot.ctx = applicationContext;
//        System.out.println(ctx.getApplicationName());
//    }
//    public static ApplicationContext getApplicationContext() {
//        return ctx;
//    }
//
//    public static void autowire(Object bean) {
//        ctx.getAutowireCapableBeanFactory().autowireBean(bean);
//    }
//    public void init(){
//        if (telegramService == null) {
//            MyBot.autowire(this);
//        }
//    }


    protected abstract void SendMSG();
////Для деплоя
//    @Override
//    public String getBotUsername() {
//        return "master_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "";
//    }

    //Для тестов
    @Override
    public String getBotUsername() {
        return "TestMasterHaus_bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }


    //Прием сообщение от бота
    @Override
    public void onUpdateReceived(Update update) {
//        telegramService = new TelegramService();
        //Если сообщение является сообщением от пользова то обрабатываем его
        if (update.hasMessage()) {

            String chatid = update.getMessage().getChatId().toString();

            telegramService.setChatId(chatid,update.getMessage().getFrom().getUserName());

            String message = update.getMessage().getText();

            //Проверка установлен ли город у пользователя
            if(telegramService.getIsCitys(update.getMessage().getChatId().toString())){

                //Проверка есть ли у пользователя привязанный профиль
                if(telegramService.getIsPersons(update.getMessage().getChatId().toString())){
//                    if (message.equals("Категории заявок")) {
//
//                        sendFatherCagoryWorks(update.getMessage().getChatId().toString(), message);
//
//                    }
                    if (message.equals("Мои категории заявок")) {

                        sendMyCategoryWorcs(update.getMessage().getChatId().toString(), message);

                    }
                    if (message.equals("Открытые заявки")) {

                        sendMyWorcsOpen(update.getMessage().getChatId().toString());

                    }
                    if (message.equals("Информация")){

                        sendInformationUserAuthorise(update);
                    }
                    if (message.equals("/start") || message.equals("/Start")){

                        sendStartInformationUser(update);
                    }
                }else {
                    if(message.equals("Заявки в городе")){
                        //идет отправка заявок в указанном городе пользователя при авторизации города
                    sendWorksInCityAuthoriseNull(update.getMessage().getChatId().toString());
                    }
                    if (message.equals("Выбрать город")){

                        sendCityInSustems(update);
                    }
                    if (message.equals("Информация")){

                        sendInformationUserAuthoriseNull(update);
                    }
                    if (message.equals("/start") || message.equals("/Start")){

                        sendStartInformationUserAothoriseNull(update);
                    }
                }

            }else {
                if (message.equals("Информация")){

                    sendInformationUserAuthoriseNull(update);
                }
                if (message.equals("Выбрать город")){

                    sendCityInSustems(update);
                }
                if (message.equals("/start") || message.equals("/Start")){

                    sendStartInformationUserAothoriseNull(update);
                }
            }
//            else {
//
//                sendMsg(update.getMessage().getChatId().toString(), message);
////              ThreadClass thread = new ThreadClass(update.getMessage());
//            }
        } else

            //Если сообщение является ответом пользователя по кнопке жмак
            if (update.hasCallbackQuery()) {

                telegramService.setChatId(update.getCallbackQuery().getMessage().getChatId().toString(),update.getCallbackQuery().getMessage().getFrom().getUserName());
                String[] message = update.getCallbackQuery().getData().split(":");


                //Проверка авторизации пользователя
                //Проверка указан ли у пользователя город
                if(telegramService.getIsCitys(update.getCallbackQuery().getMessage().getChatId().toString())) {

                    //Проверка есть ли у пользователя привязанный профиль
                    if (telegramService.getIsPersons(update.getCallbackQuery().getMessage().getChatId().toString())) {

                        //Работы выданные пользователю по указанной категории работ
                        if(message[0].equals("#CBW")){
                            sendWorksBuy(update.getCallbackQuery().getMessage().getChatId().toString(), Integer.valueOf(message[1]),update.getCallbackQuery().getId().toString(),update);
                            //Покупка работы
                        }

                        //Покупка "Кнопка"
                        if(message[0].equals("#CBWO")){
                            WorksBuy(update.getCallbackQuery().getMessage().getChatId().toString(), Long.valueOf(message[1]),update.getCallbackQuery().getId().toString(),update);
                        }

                        //Отмена покупки "Кнопка"
                        if(message[0].equals("#CBWF")){
                            sendWorksBuyFalse(update);
                        }

                        //Выдача работ по выбранной категории
                        if(message[0].equals("#CCA")){
                            sendWorksCategory(update.getCallbackQuery().getMessage().getChatId().toString(), Integer.valueOf(message[1]),update.getCallbackQuery().getId().toString());
                        }
                        //Категории низкого уровня входной параметр родительская категория
                        if (message[0].equals("#CFA")) {
                            sendChildCagoryWorks(update.getCallbackQuery().getMessage().getChatId().toString(), Long.valueOf(message[1]),update.getCallbackQuery().getId().toString());
                        }

                        //Закрыть открытую заявку пользователя
                        if (message[0].equals("#WClOS")) {
                            sendClosedWorc(update, Long.valueOf(message[1]),update.getCallbackQuery().getId().toString());
                        }

                        //Вопрос перед закрытием, защита от дураков, Закрыть открытую заявку пользователя
                        if (message[0].equals("#WClOSQ")) {
                            sendClosedWorcQuestion(update, Long.valueOf(message[1]),update.getCallbackQuery().getId().toString());
                        }

                    }
                }

                //Запрос на установку города пользователю телеги
                if(message[0].equals("#C")){

                    sendCitysManager(update.getCallbackQuery().getMessage().getChatId().toString(), Long.valueOf(message[1]),update.getCallbackQuery().getId().toString());
                }
//                else {
//                    //AnswerCallbackThread answerThread = new AnswerCallbackThread(update.getCallbackQuery());
//                    answerCallbackQuery(update.getCallbackQuery().getId(), update.getCallbackQuery().getData() + update.getCallbackQuery().getMessage().getText());
//                }

            }

    }

    //Рабочая схема создания кнопок
    //region
    //Обработка ответа содержащим дату по кнопке и отправка
    public synchronized void answerCallbackQuery(String callbackId, String message) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);
        answer.setShowAlert(false);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    //Обработка сообщения от пользователя и отправка
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s + chatId);
        sendMessage = setButtons(sendMessage);
//        sendMessage.setReplyMarkup(setInline());
        sendMessage.setParseMode("HTML");
        sendMessage.setText("<b>Ваше сообщение</b>\n" + s+ "\n"
                + "<b>Ваш чат ID</b>\n " + chatId + "\n");

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInline() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("“Кнопка“").setCallbackData("7"));
        buttons.add(buttons1);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }

    //Установка кнопок под клавиатурой
    public synchronized SendMessage setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        //keyboardFirstRow.add(new KeyboardButton("Категории заявок"));
        keyboardFirstRow.add(new KeyboardButton("Мои категории заявок"));
        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Открытые заявки"));
        keyboardSecondRow.add(new KeyboardButton("Информация"));
        keyboard.add(keyboardSecondRow);




        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return sendMessage;
    }
    //endregion

    //Кнопка Категории работ
    //region

    //Обработка запроса на родительскую категорию
    //region
    //Обработка сообщения от пользователя и отправка
    public synchronized void sendFatherCagoryWorks(String chatId, String s) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);



        List<Categorys> father = null;
        father = telegramService.getFatherCategory(chatId);
//        sendMessage = setButtons(sendMessage);
        if(father.size()!=0){
            sendMessage.setReplyMarkup(setInlineCategoryWorkFatherCategory(father));
            sendMessage.setText("Выберите категорию:");
        }
        else {
            sendMessage.setText("У Вашего менеджера нет открытых заявок, мы можем предположить что он плохо работает, возможно необходимо его взбодрить :)");
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineCategoryWorkFatherCategory(List<Categorys> father) {

//        telegramService = new TelegramService();






        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();

        InlineKeyboardMarkup markupKeyboard = null;

        int size = 0;
        if(!father.equals(null)){
            for (Categorys ca : father) {

                if (size == 1) {
                    buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#CFA:" + ca.getId().toString()));
                    buttons.add(buttons1);
                    size = 0;
                } else {
                    buttons1 = new ArrayList<>();
                    buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#CFA:" + ca.getId().toString()));
                    size = 1;
                    if(father.size()==1){
                        buttons.add(buttons1);
                    }
                }

            }
            markupKeyboard = new InlineKeyboardMarkup();
            markupKeyboard.setKeyboard(buttons);
        }
        return markupKeyboard;
    }
    //endregion

    //Обработка запроса на дочернюю категорию
    //region
    //Обработка сообщения от пользователя и отправка
    public synchronized void sendChildCagoryWorks(String chatId, Long father,String callbackId ) {

        answerChildCallbackQuery(callbackId, "Ок");

        ArrayList<Categorys> Child = telegramService.getChildCategory(father, chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText("Выберите подкатегорию "+telegramService.getCategoryId(father).getName()+":");
//        sendMessage = setButtons(sendMessage);
        sendMessage.setReplyMarkup(setInlineCategoryWorkChildCategory(Child));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }

    //Обработка ответа содержащим дату по кнопке и отправка
    public void answerChildCallbackQuery(String callbackId, String message) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);
        answer.setShowAlert(false);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineCategoryWorkChildCategory(ArrayList<Categorys> Child) {



        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();

        int size = 0;
        if(Child.size()==0){

        } if(Child.size()==1){
            buttons1 = new ArrayList<>();
            buttons1.add(new InlineKeyboardButton().setText(Child.get(0).getName()).setCallbackData("#CCA:" + Child.get(0).getId().toString()));
            buttons.add(buttons1);
        }else{
            for (Categorys ca : Child) {

                if (size == 1) {
                    buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#CCA:" + ca.getId().toString()));
                    buttons.add(buttons1);
                    size = 0;
                } else {
                    buttons1 = new ArrayList<>();
                    buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#CCA:" + ca.getId().toString()));
                    size = 1;
                }

            }
        }
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
    //endregion


    //endregion

    //Вывод заявок доступных к покупке
    //region
    public synchronized void sendWorksCategory(String chatId, Integer father,String callbackId ) {

        answerChildCallbackQuery(callbackId, "Ок");

        List<Worcs> worcs = telegramService.getWorksCategory(Long.valueOf(father), chatId);


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        if(worcs.size() == 0)
        {
            sendMessage.setText("В данной категории откытых заявок <b>не найденно</b>\n");


            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }else {
            for (Worcs w : worcs
            ) {
                sendMessage.setText("<b>Название</b>\n" + w.getName() + "\n"
                        + "<b>Подробное описание</b>\n" + w.getFullname() + "\n"
                        + "<b>Стоимость</b>: " + w.getPrice() + "\n"
                        + "<b>Район</b>: " + w.getRon() + "\n");
                //        sendMessage = setButtons(sendMessage);
                sendMessage.setReplyMarkup(setInlineWorkBuy(w.getId(), w.getPrice(), w.getCategorys().getPercent()));

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }
            }
        }

    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineWorkBuy(Long id, double price, double percent) {


        ArrayList<Categorys> Child = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();


        buttons1.add(new InlineKeyboardButton().setText("Купить заявку за: "+ getPercent(price, percent) +" руб").setCallbackData("#CBW:" + id));
        buttons.add(buttons1);


        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }

    //endregion

    //Покупка заявки
    //region
    public synchronized void sendWorksBuy(String chatId, Integer father,String callbackId, Update update ) {

        answerChildCallbackQuery(callbackId, "Ок");

        sendWorksBuyFalse(update);


        Worcs worcs = telegramService.getWorksId(Long.valueOf(father));


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

         sendMessage.setText("<b>Название</b>\n" + worcs.getName() + "\n"
                        + "<b>Подробное описание</b>\n" + worcs.getFullname() + "\n"
                        + "<b>Стоимость</b>: " + worcs.getPrice() + "\n"
//                        + "<b>Район</b>: " + worcs.getRon() + "\n"
                        + "\n"
                        + "Дождитесь подтверждения\n");
                //        sendMessage = setButtons(sendMessage);
          sendMessage.setReplyMarkup(setInlineWorkBuyOk(worcs.getId(), worcs.getPrice(), worcs.getCategorys().getPercent()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//              log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }



    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineWorkBuyOk(Long id, double price, double percent) {

        ArrayList<Categorys> Child = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();


        buttons1.add(new InlineKeyboardButton().setText("Подтверждаю: "+ getPercent(price, percent) +" руб").setCallbackData("#CBWO:" + id));
        buttons1.add(new InlineKeyboardButton().setText("Отмена покупки").setCallbackData("#CBWF:" + id));
        buttons.add(buttons1);


        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
    //endregion

    //Покупка работы
    //region
    public synchronized void WorksBuy(String chatId, Long wid, String callbackId,Update update) {
        answerChildCallbackQuery(callbackId, "Проверка специалиста");
        //Проверка существует ля связанный аккаунт у пользователя

        sendWorksBuyFalse(update);

        if(telegramService.getIsPersons(chatId))
        {
            //Покупка заявки прошла успешно, денег хватает, счета существуют
            if(telegramService.Pay(chatId, wid)){



                Worcs worcs = telegramService.getWorksId(wid);


                SendMessage sendMessage = new SendMessage();
                sendMessage.enableMarkdown(true);
                sendMessage.setChatId(chatId);
                sendMessage.setParseMode("HTML");



                Areas areas1 = telegramService.getAreasWorc(wid);
                Suburbs suburbs1 = telegramService.getSuburbsWorc(wid);
                String areas = "none";

                if(areas1!=null){
                    areas = areas1.getAreasname();
                }
                String suburbs = "none";

                if(suburbs1!=null){
                    suburbs = suburbs1.getName();
                }

                String parametrMessage = String.format("<b>" + worcs.getName() +"</b>\n\n"
                        + "<b>" + worcs.getFullname() + "</b>\n\n"
                        + "<b>Стоимость выполнения:</b> " + worcs.getPrice() + "\n"
                        + "<b>Город</b>: " + worcs.getCitys().getName() + "\n"
//                        + "<b>Пригород</b>: " + suburbs + "\n"
//                        + "<b>Район</b>: " + areas + "\n"
                        + "<b>Заказчик ФИО</b>: " + worcs.getFio() + "\n"
                        + "<b>Телефон для связи</b>: +" + worcs.getPhone() + "\n"
                        + "<b>Улица</b>: " + worcs.getStreet() + "\n"
                        + "<b>Дом</b>: " + worcs.getHouse() + "\n"
                        + "<b>Квартира</b>: " + worcs.getApartment() + "\n"
                        + "\n"
                        + "После выполнения не забудьте закрыть заявку\n");

                Areas a=worcs.getAreas();
                if(a!=null){
                    parametrMessage = String.format("<b>" + worcs.getName() +"</b>\n\n"
                            + "<b>" + worcs.getFullname() + "</b>\n\n"
                            + "<b>Стоимость выполнения:</b> " + worcs.getPrice() + "\n"
                            + "<b>Город</b>: " + worcs.getCitys().getName() + "\n"
                            + "<b>Район</b>: " + a.getAreasname() + "\n"
                            + "<b>Заказчик ФИО</b>: " + worcs.getFio() + "\n"
                            + "<b>Телефон для связи</b>: +" + worcs.getPhone() + "\n"
                            + "<b>Улица</b>: " + worcs.getStreet() + "\n"
                            + "<b>Дом</b>: " + worcs.getHouse() + "\n"
                            + "<b>Квартира</b>: " + worcs.getApartment() + "\n"
                            + "\n"
                            + "После выполнения не забудьте закрыть заявку\n");
                }
                Suburbs s = worcs.getSuburbs();
                if(s!=null){
                    parametrMessage = String.format("<b>" + worcs.getName() +"</b>\n\n"
                            + "<b>" + worcs.getFullname() + "</b>\n\n"
                            + "<b>Стоимость выполнения:</b> " + worcs.getPrice() + "\n"
                            + "<b>Город</b>: " + worcs.getCitys().getName() + "\n"
                            + "<b>Пригород</b>: " + s.getName() + "\n"
                            + "<b>Заказчик ФИО</b>: " + worcs.getFio() + "\n"
                            + "<b>Телефон для связи</b>: +" + worcs.getPhone() + "\n"
                            + "<b>Улица</b>: " + worcs.getStreet() + "\n"
                            + "<b>Дом</b>: " + worcs.getHouse() + "\n"
                            + "<b>Квартира</b>: " + worcs.getApartment() + "\n"
                            + "\n"
                            + "После выполнения не забудьте закрыть заявку\n");
                }

                sendMessage.setText(parametrMessage);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//              log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }
            }else {
                //Не хватает денег на сччете
                Persons persons = telegramService.getPersons(chatId);

                SendMessage sendMessage = new SendMessage();
                sendMessage.enableMarkdown(true);
                sendMessage.setChatId(chatId);
                sendMessage.setParseMode("HTML");

                sendMessage.setText("Не достаточно средств на счете\n"
                        + "Пополните счет\n"
                        + "Ваш ID: " +chatId+"\n"
                        + "Ваш менеджер: "+persons.getManagers().getFamilyname() +" "+persons.getManagers().getFirstname()+" "+ persons.getManagers().getLastname() +"\n"
                        + "Номер телефона менеджера: +"+persons.getManagers().getPhone()+" \n"
                        + "С уважением команда WorkHaus.online \n");
                //        sendMessage = setButtons(sendMessage);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//              log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }
            }
        }else {
            //Связанный профиль не найден
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(chatId);
            sendMessage.setParseMode("HTML");

            sendMessage.setText("Вы не авторизованны в системе\n"
                    + "Покупка заявки не возможна\n"
                    + "Обратитесь к своему менеджеру\n\n\n"
                    + "Если у Вас есть желание зарабатывать, и вы являетесь ответственным специалистом \n"
                    + "Команда WorkHaus.online \n"
                    + "Будет рада видеть Вас в своем составе\n"
                    + "Для получения дополнительной информации по поводу сотрудничества\n"
                    + "Телефон: +7 962 318 00 31\n");
            //        sendMessage = setButtons(sendMessage);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//              log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }
    }
    //endregion


    //Информация
    //region
    public synchronized void sendInformationUser(Update update ) {

//        answerChildCallbackQuery(callbackId, "Ок");

//        ArrayList<Categorys> Child = telegramService.getChildCategory(father);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("HTML");

        sendMessage.setText("<b>Чат ID: </b>"+update.getMessage().getChatId().toString()+"\n"
                + "<b>Имя: </b>"+update.getMessage().getFrom().getFirstName()+"\n"
                + "<b>Фамилия: </b>"+update.getMessage().getFrom().getLastName()+"\n");
//                + "<b>Имя телеграмм: </b>"+update.getMessage().getFrom().getUserName()+" \n");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }
    //endregion

    //Кнопка старт
    //region
    public synchronized void sendStartInformationUser(Update update ) {

//        answerChildCallbackQuery(callbackId, "Ок");

//        ArrayList<Categorys> Child = telegramService.getChildCategory(father);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage = setButtons(sendMessage);
        sendMessage.setParseMode("HTML");

        Persons persons = telegramService.getPersons(update.getMessage().getChatId().toString());
        sendMessage.setText("<b>Чат ID: </b>"+update.getMessage().getChatId().toString()+"\n"
//                + "<b>Имя телеграмм: </b>"+update.getMessage().getFrom().getUserName()+" \n"
                + "<b>Добро пожаловать </b> "+persons.getName()+" "+persons.getSurname()+"  для работы с ботом воспользуйтесть специальной клавиатурой");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }
    //endregion

    //Первое попадание пользователя в систему, не указан ни город ни профиль специалиста команда /start
    //region
    public synchronized void sendStartInformationUserAothoriseNull(Update update ) {

//        answerChildCallbackQuery(callbackId, "Ок");

//        ArrayList<Categorys> Child = telegramService.getChildCategory(father);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage = setButtonsAothoriseNull(sendMessage);
        sendMessage.setParseMode("HTML");

        sendMessage.setText("Вы не авторизованы в системе как специалист\n\n"
                + "Укажите город Вашего нахождения при помощи встроенной клавиатуры раздел Выбрать город\n\n"
                + "Вы получите список городов в которых присутствует менеджер\n"
                + "Выберите город и получите возможность получать информацию о доступных в " +
                "данном городе заявках, а так же контактные данные менеджера работающего в этом городе \n");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }

    //Установка кнопок под клавиатурой
    public synchronized SendMessage setButtonsAothoriseNull(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Выбрать город"));
        keyboardFirstRow.add(new KeyboardButton("Информация"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("/start"));
        keyboard.add(keyboardSecondRow);



        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return sendMessage;
    }
    //endregion

    //Команда обновить города
    //region
    public synchronized void sendCityInSustems(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите город:");
//        sendMessage = setButtons(sendMessage);
        sendMessage.setReplyMarkup(setInlineCity());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineCity() {

//        telegramService = new TelegramService();

        List<Citys> citys = telegramService.getCitysManagers();

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();

        int size = 0;

        int count = citys.size();
        int counter = 1;

        int del = 2;
        int breaker = 1;


        //int q = s/del;
        //int w = q*del;



        for (Citys ca : citys) {

            buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#C:" + ca.getId().toString()));


            if(counter == count&&breaker != del){
                buttons.add(buttons1);
            }

            if(breaker == del){
                buttons.add(buttons1);
                breaker = 0;
                buttons1 = new ArrayList<>();
            }

            counter++;
            breaker++;

//            buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#C:" + ca.getId().toString()));
//
//            if(s=)

//                count++;

//            if (size == 1) {
//                buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#C:" + ca.getId().toString()));
//                buttons.add(buttons1);
//                size = 0;
//            } else {
//                buttons1 = new ArrayList<>();
//                buttons1.add(new InlineKeyboardButton().setText(ca.getName()).setCallbackData("#C:" + ca.getId().toString()));
//                size = 1;
//                if(citys.size()==1){
//                    buttons.add(buttons1);
//                }
//            }

        }

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
    //endregion

    //Команда установить город пользователю
    //region
    public synchronized void sendCitysManager(String chatId, Long cityId,String callbackId ) {

        answerChildCallbackQuery(callbackId, "Ок");

        telegramService.setCitysUser(cityId,chatId);
        Set<Managers> managers = null;
        managers = telegramService.getManagerCity(cityId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        if(managers.size()==0){
            sendMessage.setText("В указанном Вами городе отсутствуют менеджеры\n"
                    + "По поводу сотрудничества можете обратиться по номеру телефона: +7962 318 00 31\n\n"
                    + "Команда WorkHaus.online будет рада видеть Вас в своем составе\n");
        }else {

            String text = "";
            for (Managers m:managers
                 ) {
                text=text +"+"+ m.getPhone()+ "\n" + m.getFamilyname()+" "+m.getFirstname()+" "+ m.getLastname()+ "\n";
            }

            sendMessage.setText("В Вашем городе работают следующие менеджеры\n"
                            + "Для получения подробной информации позвоните по номеру телефона\n"
                            + text
                    );
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//              log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }



    }
    //endregion

    //Информация для авторизованного пользовател
    //region
    public synchronized void sendInformationUserAuthorise(Update update ) {

//        answerChildCallbackQuery(callbackId, "Ок");

//        ArrayList<Categorys> Child = telegramService.getChildCategory(father);

        Persons persons = telegramService.getPersons(update.getMessage().getChatId().toString());


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("HTML");

        sendMessage.setText("<b>Чат ID: </b>"+update.getMessage().getChatId().toString()+"\n"
//                + "<b>Имя телеграмм: </b>"+update.getMessage().getFrom().getUserName()+" \n"
                + "<b>Имя: </b>"+persons.getName()+"\n"
                + "<b>Фамилия: </b>"+persons.getFamily()+"\n"
                + "<b>Отчество: </b>"+persons.getSurname()+"\n"
                + "<b>Баланс: </b>"+persons.getCashaccounts().getCash()+"\n"
                + "<b>Менеджер </b>\n"+persons.getManagers().getFamilyname()+" "+persons.getManagers().getFirstname()+" " +persons.getManagers().getLastname()+"\n"
                + "<b>Телефон менеджера: </b>\n +7"+persons.getManagers().getPhone()+"\n");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }
    //endregion

    //Информация для не авторизованного пользователя
    //region
    public synchronized void sendInformationUserAuthoriseNull(Update update ) {

//        answerChildCallbackQuery(callbackId, "Ок");

//        ArrayList<Categorys> Child = telegramService.getChildCategory(father);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("HTML");

        sendMessage.setText("<b>Чат ID: </b>"+update.getMessage().getChatId().toString()+"\n"
                + "<b>Имя телеграмм: </b>"+update.getMessage().getFrom().getUserName()+" \n");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }
    }
    //endregion

    //Заявки в городе для не авторизованного пользователя
    //region
    public synchronized void sendWorksInCityAuthoriseNull(String chatId ) {


        Set<Worcs> worcs = telegramService.getWorcsInCity(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        if(worcs.size() == 0)
        {
            sendMessage.setText("В данной категории откытых заявок <b>не найденно</b>\n");


            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }else {
            for (Worcs w : worcs
            ) {
                sendMessage.setText("<b>Название</b>\n" + w.getName() + "\n"
                        + "<b>Подробное описание</b>\n" + w.getFullname() + "\n"
                        + "<b>Стоимость</b>: " + w.getPrice() + "\n");
                //        sendMessage = setButtons(sendMessage);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }
            }
        }

    }
    //endregion

    //Удаление сообщения о покупке
    //region
    public synchronized void sendWorksBuyFalse(Update update ) {

        answerChildCallbackQuery(update.getCallbackQuery().getId(), "Ок");

        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
//              log.log(Level.SEVERE, "Exception: ", e.toString());
            e.printStackTrace();
        }



    }
    //endregion

    //Выдача работ по указанным категориям работ исполнителя
    //region

    @Autowired
    TelegramMessageService telegramMessageService;

    public synchronized void sendMyCategoryWorcs(String chatId,String callbackId ) {

        answerChildCallbackQuery(callbackId, "Ок");

        List<Worcs> worcs = telegramService.getMyCategoryWorcs(chatId);


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        Persons persons = telegramService.getPersons(chatId);
        Set<Categorys> categorys = persons.getCategorys();
        if(categorys.size()==0){
            sendMessage.setText("У вас не выбранно не одной категории, свяжитесь с менеджером");


            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }else{
            if(worcs.size() == 0)
            {
                sendMessage.setText("К сожалению по выбранным Вами категориям работ заявок <b>не найденно</b>\n"
                        +"Наверное нужно пнуть менеджера");


                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }
            }else {
                for (Worcs w : worcs
                ) {
                    String parametrMessage = String.format("<b>Название</b>\n" + w.getName() + "\n"
                            + "<b>Подробное описание</b>\n" + w.getFullname() + "\n"
                            + "<b>Стоимость</b>: " + w.getPrice() + "\n");

                    Areas a=w.getAreas();
                    if(a!=null){
                        parametrMessage = String.format("<b>Название</b>\n" + w.getName() + "\n"
                                + "<b>Подробное описание</b>\n" + w.getFullname() + "\n"
                                + "<b>Стоимость</b>: " + w.getPrice() + "\n"
                                + "<b>Район</b>: " + w.getAreas().getAreasname() + "\n");
                    }
                    Suburbs s = w.getSuburbs();
                    if(s!=null){
                        parametrMessage = String.format("<b>Название</b>\n" + w.getName() + "\n"
                                + "<b>Подробное описание</b>\n" + w.getFullname() + "\n"
                                + "<b>Стоимость</b>: " + w.getPrice() + "\n"
                                + "<b>Пригород</b>: " + w.getSuburbs().getName() + "\n");
                    }

                    sendMessage.setText(parametrMessage);
                    //        sendMessage = setButtons(sendMessage);
                    sendMessage.setReplyMarkup(setInlineWorkBuy(w.getId(), w.getPrice(), w.getCategorys().getPercent()));

                    telegramMessageService.sendMess(sendMessage);

                }
            }
        }
    }


    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineWorkBuyMyCategory(Long id, double price, double percent) {


        ArrayList<Categorys> Child = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();


        buttons1.add(new InlineKeyboardButton().setText("Купить заявку за: "+ getPercent(price, percent) +" руб").setCallbackData("#CBW:" + id));
        buttons.add(buttons1);


        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
    //endregion

    //Выдача работ купленных исполнителем и в данный момент имеющих статус открытых
    //region
    public synchronized void sendMyWorcsOpen(String chatId ) {

//        answerChildCallbackQuery(callbackId, "Ок");

        List<Worcs> worcs = telegramService.getMyOpenWorcs(chatId);


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        if(worcs.size() == 0)
        {
            sendMessage.setText("В данный момент у Вас открытых заявок <b>не найденно</b>\n"
                    +"Приобретите заявку и она будет доступна в этом разделе до того момента пока вы ее не закроете");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }else {
            for (Worcs w : worcs
            ) {

                Areas areas1 = telegramService.getAreasWorc(w.getId());
                Suburbs suburbs1 = telegramService.getSuburbsWorc(w.getId());
                String areas = "none";

                if(areas1!=null){
                    areas = areas1.getAreasname();
                }
                String suburbs = "none";

                if(suburbs1!=null){
                    suburbs = suburbs1.getName();
                }

                String parametrMessage = String.format("<b>" + w.getName() +"</b>\n\n"
                        + "<b>" + w.getFullname() + "</b>\n\n"
                        + "<b>Стоимость выполнения:</b> " + w.getPrice() + "\n"
                        + "<b>Город</b>: " + w.getCitys().getName() + "\n"
//                        + "<b>Пригород</b>: " + suburbs + "\n"
//                        + "<b>Район</b>: " + areas + "\n"
                        + "<b>Заказчик ФИО</b>: " + w.getFio() + "\n"
                        + "<b>Телефон для связи</b>: +" + w.getPhone() + "\n"
                        + "<b>Улица</b>: " + w.getStreet() + "\n"
                        + "<b>Дом</b>: " + w.getHouse() + "\n"
                        + "<b>Квартира</b>: " + w.getApartment() + "\n"
                        + "\n"
                        + "После выполнения не забудьте закрыть заявку\n");

                Areas a=w.getAreas();
                if(a!=null){
                    parametrMessage = String.format("<b>" + w.getName() +"</b>\n\n"
                            + "<b>" + w.getFullname() + "</b>\n\n"
                            + "<b>Стоимость выполнения:</b> " + w.getPrice() + "\n"
                            + "<b>Город</b>: " + w.getCitys().getName() + "\n"
                            + "<b>Район</b>: " + a.getAreasname() + "\n"
                            + "<b>Заказчик ФИО</b>: " + w.getFio() + "\n"
                            + "<b>Телефон для связи</b>: +" + w.getPhone() + "\n"
                            + "<b>Улица</b>: " + w.getStreet() + "\n"
                            + "<b>Дом</b>: " + w.getHouse() + "\n"
                            + "<b>Квартира</b>: " + w.getApartment() + "\n"
                            + "\n"
                            + "После выполнения не забудьте закрыть заявку\n");
                }
                Suburbs s = w.getSuburbs();
                if(s!=null){
                    parametrMessage = String.format("<b>" + w.getName() +"</b>\n\n"
                            + "<b>" + w.getFullname() + "</b>\n\n"
                            + "<b>Стоимость выполнения:</b> " + w.getPrice() + "\n"
                            + "<b>Город</b>: " + w.getCitys().getName() + "\n"
                            + "<b>Пригород</b>: " + s.getName() + "\n"
                            + "<b>Заказчик ФИО</b>: " + w.getFio() + "\n"
                            + "<b>Телефон для связи</b>: +" + w.getPhone() + "\n"
                            + "<b>Улица</b>: " + w.getStreet() + "\n"
                            + "<b>Дом</b>: " + w.getHouse() + "\n"
                            + "<b>Квартира</b>: " + w.getApartment() + "\n"
                            + "\n"
                            + "После выполнения не забудьте закрыть заявку\n");
                }

                sendMessage.setText(parametrMessage);

                sendMessage.setReplyMarkup(setInlineMyWorcsOpen(w.getId()));

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }
            }
        }

    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineMyWorcsOpen(Long id) {


        ArrayList<Categorys> Child = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();

//        buttons1.add(new InlineKeyboardButton().setText("Закрыть").setCallbackData("#WClOS:" + id));
        buttons1.add(new InlineKeyboardButton().setText("Закрыть").setCallbackData("#WClOSQ:" + id));
        //buttons1.add(new InlineKeyboardButton().setText("Проблема").setCallbackData("#WClFA:" + id));
        buttons.add(buttons1);


        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
    //endregion



    //Закрыть открытую работу Специалиста
    //region
    public synchronized void sendClosedWorc(Update update,Long Wid, String qid ) {

        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        answerChildCallbackQuery(qid, "Ок");

        sendWorksBuyFalse(update);

        Worcs w = telegramService.getPersonCloseWorcs(update.getCallbackQuery().getMessage().getChatId().toString(),Wid);


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        if(w == null)
        {
            sendMessage.setText("Что то пошло не так, подтверждения закрытия заявки не пришло\n");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }else {

                Areas areas1 = telegramService.getAreasWorc(w.getId());
                Suburbs suburbs1 = telegramService.getSuburbsWorc(w.getId());
                String areas = "none";

                if(areas1!=null){
                    areas = areas1.getAreasname();
                }
                String suburbs = "none";

                if(suburbs1!=null){
                    suburbs = suburbs1.getName();
                }

                sendMessage.setText("<b>" + w.getName() +"</b>\n"
                        + "<b>" + w.getFullname() + "</b>\n"
                        + "<b>Стоимость выполнения:</b> " + w.getPrice() + "\n"
                        + "<b>Город</b>: " + w.getCitys().getName() + "\n"

                        + "<b>Заказчик ФИО</b>: " + w.getFio() + "\n"
                        + "\n\n"
                        + "Заявка успешно закрыта\n");

                //sendMessage.setReplyMarkup(setInlineMyWorcsOpen(w.getId()));

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                    e.printStackTrace();
                }

        }

    }
    //endregion

    //Вопрос перед закрытием заявки, что бы небыло косяков
    //region
    public synchronized void sendClosedWorcQuestion(Update update,Long Wid, String qid ) {

        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        answerChildCallbackQuery(qid, "Ок");

        sendWorksBuyFalse(update);

        Worcs w = telegramService.getPersonWorc(update.getCallbackQuery().getMessage().getChatId().toString(),Wid);


        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        if(w == null)
        {
            sendMessage.setText("Что то пошло не так, подтверждения закрытия заявки не пришло\n");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }
        }else {

            Areas areas1 = telegramService.getAreasWorc(w.getId());
            Suburbs suburbs1 = telegramService.getSuburbsWorc(w.getId());
            String areas = "none";

            if(areas1!=null){
                areas = areas1.getAreasname();
            }
            String suburbs = "none";

            if(suburbs1!=null){
                suburbs = suburbs1.getName();
            }

            sendMessage.setText("<b>" + w.getName() +"</b>\n"
                    + "<b>" + w.getFullname() + "</b>\n"
                    + "<b>Стоимость выполнения:</b> " + w.getPrice() + "\n"
                    + "<b>Город</b>: " + w.getCitys().getName() + "\n"

                    + "<b>Заказчик ФИО</b>: " + w.getFio() + "\n"
                    + "\n\n"
                    + "Вы точно хотите закрыть эту заявку? После закрытия заявка будет считаться выполненной\n");

            sendMessage.setReplyMarkup(setInlineClosedWorcQuestio(w.getId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
                e.printStackTrace();
            }

        }

    }

    //Установка кнопок под сообщениями
    private InlineKeyboardMarkup setInlineClosedWorcQuestio(Long id) {


        ArrayList<Categorys> Child = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();

        buttons1.add(new InlineKeyboardButton().setText("Подтверждаю").setCallbackData("#WClOS:" + id));
        buttons1.add(new InlineKeyboardButton().setText("Отмена").setCallbackData("#CBWF:" + id));
        buttons.add(buttons1);


        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
    //endregion

    private int getPercent(double price, double percent){
        double i = price/100;
        double f = i*percent;
        int b = (int)f;
        return b;
    }

    //Принудительная отправка сообщений
    //region
    public void sendEvent(String chatId, String text){
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setParseMode("HTML");
        message.setChatId(Long.parseLong(chatId));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //endregion

}


