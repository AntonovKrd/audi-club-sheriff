package krd.antonov.audiclubsheriff.telegram.handlers;

import krd.antonov.audiclubsheriff.exceptions.TelegramCreateChatLinkException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.exceptions.UserNotFoundException;
import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.constants.CommandConstants;
import krd.antonov.audiclubsheriff.telegram.service.ManageUsersService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallbackQueryHandler {

    private final ManageUsersService manageUsersService;

    public CallbackQueryHandler(ManageUsersService manageUsersService) {
        this.manageUsersService = manageUsersService;
    }

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws UserNotFoundException, TelegramSendMessageException, TelegramCreateChatLinkException {
        String command = buttonQuery.getData().substring(0, buttonQuery.getData().indexOf("_") + 1);
        String data = buttonQuery.getData().replace(command, "");
        AnswerCallbackQuery answer = null;
        switch (command) {
            case CommandConstants.ACCEPT_USER -> {
                manageUsersService.activateUser(data);
                answer = getCallbackAnswer(buttonQuery.getId(), BotMessageEnum.ACCEPT_ADMIN_MESSAGE.getMessage());
            }
            case CommandConstants.DECLINE_USER -> {
                manageUsersService.declineUser(data);
                answer = getCallbackAnswer(buttonQuery.getId(), BotMessageEnum.DECLINE_ADMIN_MESSAGE.getMessage());
            }
        }
        return answer;
    }

    private AnswerCallbackQuery getCallbackAnswer(String id, String text) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(id);
        answerCallbackQuery.setText(text);
        answerCallbackQuery.setShowAlert(true);
        return answerCallbackQuery;
    }
}
