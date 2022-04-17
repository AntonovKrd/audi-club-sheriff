package krd.antonov.audiclubsheriff.telegram;

import krd.antonov.audiclubsheriff.exceptions.*;
import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.handlers.CallbackQueryHandler;
import krd.antonov.audiclubsheriff.telegram.handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
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
    private CallbackQueryHandler callbackQueryHandler;
    public AudiSheriffBot(SetWebhook setWebhook, MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
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

    private BotApiMethod<?> handleUpdate(Update update) throws TelegramSendMessageException, TempDataNotFoundException, TelegramSendPhotoException, UserNotFoundException, TelegramCreateChatLinkException {
        BotApiMethod<?> answer = null;
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            answer = callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message != null) {
                answer = messageHandler.handleMessage(message);
            }
        }
        return answer;
    }

}
