package krd.antonov.audiclubsheriff.telegram.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonNameEnum {

    REGISTRATION_BUTTON("Регистрация"),
    EDIT_DATA_BUTTON("Изменить данные");

    private final String button;
}
