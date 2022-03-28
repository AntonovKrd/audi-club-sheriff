package krd.antonov.audiclubsheriff.telegram.handlers;

import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.keyboards.MainMenuKeyBoard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler {

    private final MainMenuKeyBoard mainMenuKeyBoard;

    public MessageHandler(MainMenuKeyBoard mainMenuKeyBoard) {
        this.mainMenuKeyBoard = mainMenuKeyBoard;
    }

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();
        String inputText = message.getText();
        SendMessage sendMessage = new SendMessage();
        if (inputText.equals("/start")) {
            sendMessage = getStartMessage(chatId);
        }
        return sendMessage;
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.START_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(mainMenuKeyBoard.get());
        return sendMessage;
    }
}
