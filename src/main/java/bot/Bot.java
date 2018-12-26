package bot;

import data.excel.GoogleSheetReader;
import data.excel.IBookInfReader;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.LongPollingBot;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private final static Logger logger = Logger.getLogger(Bot.class);

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        logger.info("Sent message " + message + " to user " + "[" + update.getMessage().getFrom().getUserName() + "]");
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    private synchronized void sendMsg(String chatId, String s) {
        IBookInfReader bookreader = new GoogleSheetReader();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(bookreader.getAllBooks().get(0).toString());
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            logger.debug("Exception: ", e);
        }
    }

    public String getBotUsername() {
        return "wroclawbookbot";
    }

    public String getBotToken() {
        return "732291859:AAFwCONGQz0TK2K8I03mQAGmjFOvo4P5pI4";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(Bot.getBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private static LongPollingBot getBot() {
        return new Bot();
    }

}
