package krd.antonov.audiclubsheriff.telegram.handlers;

import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.constants.CommandConstants;
import krd.antonov.audiclubsheriff.telegram.keyboards.KeyboardBuilder;
import krd.antonov.audiclubsheriff.telegram.service.TelegramApiService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler {

    private final KeyboardBuilder mainMenuKeyBoard;
    private final TelegramApiService telegramApiService;

    public MessageHandler(KeyboardBuilder mainMenuKeyBoard, TelegramApiService telegramApiService) {
        this.mainMenuKeyBoard = mainMenuKeyBoard;
        this.telegramApiService = telegramApiService;
    }

    public BotApiMethod<?> answerMessage(Message message) throws TelegramSendMessageException {
        String chatId = message.getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        switch (message.getText()) {
            case CommandConstants.START:
                sendMessage = getStartMessage(chatId);
                break;
            case CommandConstants.REGISTRATION:
                telegramApiService.sendMessage(new SendMessage(message.getChatId().toString(), BotMessageEnum.REGISTRATION_HELP_MESSAGE.getMessage()));
                sendMessage = initRegistration(chatId);
                break;
            case CommandConstants.EDIT_DATA:
                break;
            default:
                break;
        }
        return sendMessage;
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.START_MESSAGE.getMessage());
        setMainMenuKeyboard(sendMessage);
        return sendMessage;
    }

    private SendMessage initRegistration(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_CONTACT_MESSAGE.getMessage());
        setRequestContactKeyboard(sendMessage);
        return sendMessage;
    }

    private void setMainMenuKeyboard(SendMessage sendMessage) {
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(mainMenuKeyBoard.getMainMenuKeyboard());
    }

    private void setRequestContactKeyboard(SendMessage sendMessage) {
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(mainMenuKeyBoard.getRequestContactKeyboard());
    }
}
