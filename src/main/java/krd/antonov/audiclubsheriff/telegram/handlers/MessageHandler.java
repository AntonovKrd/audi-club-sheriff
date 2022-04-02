package krd.antonov.audiclubsheriff.telegram.handlers;

import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.exceptions.TempDataNotFoundException;
import krd.antonov.audiclubsheriff.model.TempData;
import krd.antonov.audiclubsheriff.service.TempDataService;
import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.constants.CommandConstants;
import krd.antonov.audiclubsheriff.telegram.constants.StagesUserDataConstants;
import krd.antonov.audiclubsheriff.telegram.keyboards.KeyboardSetter;
import krd.antonov.audiclubsheriff.telegram.service.ManageUsersService;
import krd.antonov.audiclubsheriff.telegram.service.TelegramApiService;
import krd.antonov.audiclubsheriff.util.TempDataChecker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler {

    private final KeyboardSetter keyboardSetter;
    private final TelegramApiService telegramApiService;
    private final TempDataService tempDataService;
    private final ManageUsersService manageUsersService;

    public MessageHandler(KeyboardSetter keyboardSetter, TelegramApiService telegramApiService, TempDataService tempDataService, ManageUsersService manageUsersService) {
        this.keyboardSetter = keyboardSetter;
        this.telegramApiService = telegramApiService;
        this.tempDataService = tempDataService;
        this.manageUsersService = manageUsersService;
    }

    public BotApiMethod<?> handleMessage(Message message) throws TelegramSendMessageException, TempDataNotFoundException {
        BotApiMethod<?> botApiMethod;
        if (message.hasText()) {
            botApiMethod = produceTextMessage(message.getText(), message.getChatId().toString());
        } else if (message.hasContact()) {
            botApiMethod = produceContact(message.getContact(), message.getChatId().toString());
        } else {
            throw new IllegalArgumentException();
        }
        return botApiMethod;
    }

    private SendMessage produceTextMessage(String message, String chatId) throws TelegramSendMessageException, TempDataNotFoundException {
        SendMessage sendMessage;
        switch (message) {
            case CommandConstants.START -> {
                tempDataService.deleteTempDataByChatId(chatId);
                sendMessage = new SendMessage(chatId, BotMessageEnum.START_MESSAGE.getMessage());
                keyboardSetter.setMainMenuKeyboard(sendMessage);
            }
            case CommandConstants.REGISTRATION -> {
                telegramApiService.sendMessage(new SendMessage(chatId, BotMessageEnum.REGISTRATION_HELP_MESSAGE.getMessage()));
                tempDataService.deleteTempDataByChatId(chatId);
                sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_USER_CONTACT_MESSAGE.getMessage());
                keyboardSetter.setRequestContactKeyboard(sendMessage);
            }
            case CommandConstants.EDIT_DATA -> {
                sendMessage = new SendMessage(chatId, BotMessageEnum.NOT_READY_FUNCTIONAL.getMessage());
                keyboardSetter.setMainMenuKeyboard(sendMessage);
            }
            default -> sendMessage = produceNonCommandTextMessage(chatId, message);
        }
        return sendMessage;
    }

    private SendMessage produceContact(Contact contact, String chatId) {
        tempDataService.create(
                chatId,
                StagesUserDataConstants.USER_PHONE_DB_STAGE,
                TempDataChecker.reformatPhone(contact.getPhoneNumber()));
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_USER_NAME_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(null);
        return sendMessage;
    }

    private SendMessage produceNonCommandTextMessage(String chatId, String message) throws TempDataNotFoundException, TelegramSendMessageException {
        TempData lastTempData = tempDataService.getLastStageTempDataByChatId(chatId);
        System.out.println(lastTempData.toString());
        SendMessage sendMessage = null;
        switch (lastTempData.getStage()) {
            case 1 -> {
                if (TempDataChecker.isNameCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.USER_NAME_DB_STAGE, message);
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_USER_DATE_BIRTH_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_USER_NAME_MESSAGE.getMessage());
                }
            }
            case 2 -> {
                if (TempDataChecker.isDateBirthCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.USER_DATE_BIRTH_DB_STAGE, message);
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_MODEL_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_USER_DATE_BIRTH_MESSAGE.getMessage());
                }
            }
            case 3 -> {
                tempDataService.create(chatId, StagesUserDataConstants.VEHICLE_MODEL_DB_STAGE, message);
                sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_YEAR_MESSAGE.getMessage());
            }
            case 4 -> {
                if (TempDataChecker.isYearCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.VEHICLE_YEAR_DB_STAGE, message);
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_LICENSE_PLATE_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_VEHICLE_YEAR_MESSAGE.getMessage());
                }
            }
            case 5 -> {
                tempDataService.create(chatId, StagesUserDataConstants.VEHICLE_LICENCE_PLATE_DB_STAGE, message);
                telegramApiService.sendMessage(new SendMessage(chatId, BotMessageEnum.REGISTRATION_SAVE_STAGE_MESSAGE.getMessage()));
                manageUsersService.registerUser(chatId);
                sendMessage = new SendMessage(chatId, BotMessageEnum.REGISTRATION_SUCCESS_MESSAGE.getMessage());
                keyboardSetter.setMainMenuKeyboard(sendMessage);
            }
        }
        return sendMessage;
    }
}
