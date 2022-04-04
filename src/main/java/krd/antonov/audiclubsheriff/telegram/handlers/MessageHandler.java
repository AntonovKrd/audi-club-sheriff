package krd.antonov.audiclubsheriff.telegram.handlers;

import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendPhotoException;
import krd.antonov.audiclubsheriff.exceptions.TempDataNotFoundException;
import krd.antonov.audiclubsheriff.model.TempData;
import krd.antonov.audiclubsheriff.service.TempDataService;
import krd.antonov.audiclubsheriff.service.UserService;
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
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
public class MessageHandler {

    private final KeyboardSetter keyboardSetter;
    private final TelegramApiService telegramApiService;
    private final TempDataService tempDataService;
    private final ManageUsersService manageUsersService;
    private final UserService userService;

    public MessageHandler(KeyboardSetter keyboardSetter, TelegramApiService telegramApiService, TempDataService tempDataService, ManageUsersService manageUsersService, UserService userService) {
        this.keyboardSetter = keyboardSetter;
        this.telegramApiService = telegramApiService;
        this.tempDataService = tempDataService;
        this.manageUsersService = manageUsersService;
        this.userService = userService;
    }

    public BotApiMethod<?> handleMessage(Message message) throws TelegramSendMessageException, TempDataNotFoundException, TelegramSendPhotoException {
        BotApiMethod<?> botApiMethod;
        if (message.hasText()) {
            botApiMethod = produceTextMessage(message, message.getChatId().toString());
        } else if (message.hasContact()) {
            botApiMethod = produceContact(message.getContact(), message.getChatId().toString());
        } else if (message.hasPhoto()) {
            botApiMethod = producePhoto(message.getPhoto(), message.getChatId().toString());
        } else {
            throw new IllegalArgumentException();
        }
        return botApiMethod;
    }

    private SendMessage produceTextMessage(Message message, String chatId) throws TelegramSendMessageException, TempDataNotFoundException {
        SendMessage sendMessage;
        switch (message.getText()) {
            case CommandConstants.START -> {
                tempDataService.deleteTempDataByChatId(chatId);
                sendMessage = new SendMessage(chatId, BotMessageEnum.START_MESSAGE.getMessage());
                if (!userService.existsByChatId(chatId)) {
                    keyboardSetter.setRegistrationKeyboard(sendMessage);
                } else {
                    keyboardSetter.setEditDataKeyboard(sendMessage);
                }
            }
            case CommandConstants.REGISTRATION -> {
                if (!userService.existsByChatId(chatId)) {
                    tempDataService.deleteTempDataByChatId(chatId);
                    telegramApiService.sendMessage(new SendMessage(chatId, BotMessageEnum.REGISTRATION_HELP_MESSAGE.getMessage()));
                    if (message.getFrom().getUserName() != null) {
                        tempDataService.create(chatId, StagesUserDataConstants.USER_USERNAME_DB_STAGE, message.getFrom().getUserName());
                    }
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_USER_CONTACT_MESSAGE.getMessage());
                    keyboardSetter.setRequestContactKeyboard(sendMessage);
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REGISTRATION_REJECTED_MESSAGE.getMessage());
                    keyboardSetter.setEditDataKeyboard(sendMessage);
                }
            }
            case CommandConstants.EDIT_DATA -> {
                sendMessage = new SendMessage(chatId, BotMessageEnum.NOT_READY_FUNCTIONAL.getMessage());
                keyboardSetter.setEditDataKeyboard(sendMessage);
            }
            default -> sendMessage = produceNonCommandTextMessage(chatId, message.getText());
        }
        return sendMessage;
    }

    private SendMessage produceContact(Contact contact, String chatId) {
        tempDataService.create(chatId, StagesUserDataConstants.USER_PHONE_DB_STAGE, TempDataChecker.reformatPhone(contact.getPhoneNumber()));
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_USER_NAME_MESSAGE.getMessage());
        keyboardSetter.removeKeyboard(sendMessage);
        return sendMessage;
    }

    private SendMessage producePhoto(List<PhotoSize> photoSizeList, String chatId) throws TempDataNotFoundException, TelegramSendMessageException, TelegramSendPhotoException {
        SendMessage sendMessage;
        if (tempDataService.getLastStageTempDataByChatId(chatId).getStage() == 7) {
            telegramApiService.sendMessage(new SendMessage(chatId, BotMessageEnum.REGISTRATION_SAVE_STAGE_MESSAGE.getMessage()));
            manageUsersService.registerUserWithVehicle(chatId);
            telegramApiService.sendPhoto("182865434", photoSizeList.stream().max(Comparator.comparing(PhotoSize::getFileSize)).get().getFileId());
            sendMessage = new SendMessage(chatId, BotMessageEnum.REGISTRATION_SUCCESS_MESSAGE.getMessage());
        } else {
            sendMessage = new SendMessage(chatId, BotMessageEnum.EXCEPTION_NOT_NOW_PHOTO.getMessage());
        }
        return sendMessage;
    }

    private SendMessage produceNonCommandTextMessage(String chatId, String message) throws TempDataNotFoundException {
        TempData lastTempData = tempDataService.getLastStageTempDataByChatId(chatId);
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
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_USER_CITY_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_USER_DATE_BIRTH_MESSAGE.getMessage());
                }
            }
            case 3 -> {
                if (TempDataChecker.isCityCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.USER_CITY_DB_STAGE, message);
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_MODEL_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_USER_CITY_MESSAGE.getMessage());
                }
            }
            case 4 -> {
                if (TempDataChecker.isVehicleModelCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.VEHICLE_MODEL_DB_STAGE, message);
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_YEAR_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_VEHICLE_MODEL_MESSAGE.getMessage());
                }
            }
            case 5 -> {
                if (TempDataChecker.isYearCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.VEHICLE_YEAR_DB_STAGE, message);
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_LICENSE_PLATE_MESSAGE.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_VEHICLE_YEAR_MESSAGE.getMessage());
                }
            }
            case 6 -> {
                if (TempDataChecker.isLicensePlateCorrect(message)) {
                    tempDataService.create(chatId, StagesUserDataConstants.VEHICLE_LICENCE_PLATE_DB_STAGE, message.toUpperCase(Locale.ROOT));
                    sendMessage = new SendMessage(chatId, BotMessageEnum.REQUEST_VEHICLE_PHOTO.getMessage());
                } else {
                    sendMessage = new SendMessage(chatId, BotMessageEnum.INVALID_VEHICLE_LICENSE_PLATE_MESSAGE.getMessage());
                }
            }
        }
        return sendMessage;
    }
}
