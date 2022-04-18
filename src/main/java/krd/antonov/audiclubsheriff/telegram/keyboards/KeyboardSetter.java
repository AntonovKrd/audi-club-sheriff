package krd.antonov.audiclubsheriff.telegram.keyboards;

import krd.antonov.audiclubsheriff.telegram.constants.ButtonNameEnum;
import krd.antonov.audiclubsheriff.telegram.constants.CommandConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardSetter {

    public void setRegistrationKeyboard(SendMessage sendMessage) {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.REGISTRATION_BUTTON.getButton()));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buildKeyboard(List.of(row1)));
    }

    public void setEditDataKeyboard(SendMessage sendMessage) {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.EDIT_DATA_BUTTON.getButton()));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buildKeyboard(List.of(row1)));
    }

    public void setRequestContactKeyboard(SendMessage sendMessage) {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton(ButtonNameEnum.REQUEST_CONTACT_BUTTON.getButton());
        button.setRequestContact(true);
        row1.add(button);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buildKeyboard(List.of(row1)));
    }

    public void setAcceptInlineKeyboard(SendMessage sendMessage, String callbackData) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(getButtonWithCallbackData(ButtonNameEnum.ACCEPT_USER_BUTTON.getButton(), CommandConstants.ACCEPT + callbackData));
        rowList.add(getButtonWithCallbackData(ButtonNameEnum.DECLINE_USER_BUTTON.getButton(), CommandConstants.DECLINE + callbackData));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

    public void setInviteInlineKeyboard(SendMessage sendMessage, String inviteLink) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(getButtonWithUrl(ButtonNameEnum.JOIN_GROUP_BUTTON.getButton(), inviteLink));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

    public void removeKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
    }

    private ReplyKeyboardMarkup buildKeyboard(List<KeyboardRow> keyboardRows) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        return replyKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButtonWithCallbackData(String buttonName, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(callbackData);
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }

    private List<InlineKeyboardButton> getButtonWithUrl(String buttonName, String inviteLink) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setUrl(inviteLink);
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }

}
