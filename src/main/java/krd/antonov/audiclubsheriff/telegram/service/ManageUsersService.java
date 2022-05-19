package krd.antonov.audiclubsheriff.telegram.service;

import krd.antonov.audiclubsheriff.exceptions.*;
import krd.antonov.audiclubsheriff.model.TempData;
import krd.antonov.audiclubsheriff.model.User;
import krd.antonov.audiclubsheriff.model.Vehicle;
import krd.antonov.audiclubsheriff.service.TempDataService;
import krd.antonov.audiclubsheriff.service.UserService;
import krd.antonov.audiclubsheriff.telegram.configuration.AdminIdPropertiesConfiguration;
import krd.antonov.audiclubsheriff.telegram.constants.BotMessageEnum;
import krd.antonov.audiclubsheriff.telegram.keyboards.KeyboardSetter;
import krd.antonov.audiclubsheriff.util.DataConverter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManageUsersService {

    private final TempDataService dataService;
    private final UserService userService;
    private final KeyboardSetter keyboardSetter;
    private final TelegramApiService telegramApiService;

    private final AdminIdPropertiesConfiguration adminsChatId;

    public ManageUsersService(TempDataService dataService, UserService userService, KeyboardSetter keyboardSetter, TelegramApiService telegramApiService, AdminIdPropertiesConfiguration adminsChatId) {
        this.dataService = dataService;
        this.userService = userService;
        this.keyboardSetter = keyboardSetter;
        this.telegramApiService = telegramApiService;
        this.adminsChatId = adminsChatId;
    }

    public void registerUserWithVehicle(String chatId) throws TempDataNotFoundException {
        List<TempData> tempData = dataService.getListTempDataByChatId(chatId);
        User user = new User().setDateRegistration(LocalDateTime.now());
        Vehicle vehicle = new Vehicle();
        for (TempData data : tempData) {
            switch (data.getStage()) {
                case 0 -> user.setTgNickname("@" + data.getValue());
                case 1 -> user.setPhone(data.getValue());
                case 2 -> {
                    user.setName(data.getValue());
                    user.setChatId(data.getChatId());
                }
                case 3 -> user.setDateBirth(DataConverter.convertStringToLocalDate(data.getValue()));
                case 4 -> user.setCity(data.getValue());
                case 5 -> user.setComment(data.getValue());
                case 6 -> vehicle.setModel(data.getValue());
                case 7 -> vehicle.setYear(Integer.valueOf(data.getValue()));
                case 8 -> vehicle.setLicensePlate(data.getValue());
            }
        }
        user.addVehicle(vehicle);
        userService.update(user);
        dataService.deleteTempDataByChatId(chatId);
    }

    public void sendRequestActivationUser(String chatId, String fileId) throws UserNotFoundException, TelegramSendMessageException, TelegramSendPhotoException {
        User user = userService.getByChatId(chatId);
        StringBuilder builder = new StringBuilder().append("Заявка:\n").append(user.toString()).append("\n");
        user.getVehicles().forEach(vehicle -> builder.append(vehicle.toString()).append("\n"));
        SendMessage sendMessage = new SendMessage(adminsChatId.getDeveloper(), builder.toString());
        keyboardSetter.setAcceptInlineKeyboard(sendMessage, user.getChatId());
        telegramApiService.sendPhoto(adminsChatId.getDeveloper(), fileId, "");
        telegramApiService.sendMessage(sendMessage);
        telegramApiService.sendPhoto(adminsChatId.getAdmin(), fileId, "");
        sendMessage.setChatId(adminsChatId.getAdmin());
        telegramApiService.sendMessage(sendMessage);
    }

    public void activateUser(String chatId) throws UserNotFoundException, TelegramSendMessageException, TelegramCreateChatLinkException {
        User user = userService.getByChatId(chatId);
        user.setActive(true);
        userService.update(user);
        String inviteLink = telegramApiService.createChatInviteLink(adminsChatId.getAudilink());
        telegramApiService.sendMessage(new SendMessage(chatId, BotMessageEnum.ACCEPT_USER_INFO_MESSAGE.getMessage()));
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.ACCEPT_USER_MESSAGE.getMessage());
        keyboardSetter.setInviteInlineKeyboard(sendMessage, inviteLink);
        telegramApiService.sendMessage(sendMessage);
    }

    public void declineUser(String chatId) throws UserNotFoundException, TelegramSendMessageException {
        User user = userService.getByChatId(chatId);
        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.DECLINE_USER_MESSAGE.getMessage(), user.getName()));
        keyboardSetter.setRegistrationKeyboard(sendMessage);
        userService.deleteUser(user);
        telegramApiService.sendMessage(sendMessage);
    }

    public void saluteNewGroupUser(String chatId) {
        try {
            User user = userService.getByChatId(chatId);
            SendMessage sendMessage = new SendMessage(adminsChatId.getAudilink(), user.getName() + BotMessageEnum.WELCOME_GROUP_MESSAGE.getMessage());
            telegramApiService.sendMessage(sendMessage);
        } catch (Exception ignored) {}
    }
}
