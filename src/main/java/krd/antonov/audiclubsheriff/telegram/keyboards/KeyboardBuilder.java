package krd.antonov.audiclubsheriff.telegram.keyboards;

import krd.antonov.audiclubsheriff.telegram.constants.ButtonNameEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
public class KeyboardBuilder {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.REGISTRATION_BUTTON.getButton()));
        row1.add(new KeyboardButton(ButtonNameEnum.EDIT_DATA_BUTTON.getButton()));
        return buildKeyboard(List.of(row1));
    }

    public ReplyKeyboardMarkup getRequestContactKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton(ButtonNameEnum.REQUEST_CONTACT_BUTTON.getButton());
        button.setRequestContact(true);
        row1.add(button);
        return buildKeyboard(List.of(row1));
    }

    private ReplyKeyboardMarkup buildKeyboard(List<KeyboardRow> keyboardRows) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
