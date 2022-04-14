package krd.antonov.audiclubsheriff.telegram;

import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendPhotoException;
import krd.antonov.audiclubsheriff.exceptions.TempDataNotFoundException;
import krd.antonov.audiclubsheriff.exceptions.UserNotFoundException;
import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Setter
@Getter
public class AudiSheriffBot extends SpringWebhookBot {

    private String botToken;
    private String botPath;
    private String botUsername;

    private MessageHandler messageHandler;

    public AudiSheriffBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (IllegalArgumentException exception) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ILLEGAL_MESSAGE.getMessage());
        } catch (TempDataNotFoundException exception) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.NOT_FOUND_FUNCTIONAL.getMessage());
        } catch (Exception exception) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.WTF_EXCEPTION.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws TelegramSendMessageException, TempDataNotFoundException, TelegramSendPhotoException, UserNotFoundException {
        Message message = update.getMessage();
        BotApiMethod<?> answer = null;
        if (message != null) {
            answer = messageHandler.handleMessage(message);
        }
        return answer;
    }

}
