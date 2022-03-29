package krd.antonov.audiclubsheriff.telegram.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonNameEnum {

    REGISTRATION_BUTTON("Регистрация"),
    EDIT_DATA_BUTTON("Изменить данные"),
    REQUEST_CONTACT_BUTTON("Отправить карточка контакта");

    private final String button;
}
