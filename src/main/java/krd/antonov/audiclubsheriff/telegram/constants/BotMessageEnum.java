package krd.antonov.audiclubsheriff.telegram.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotMessageEnum {
    //start message
    START_MESSAGE("""
            Привет! Это бот #audiclubkrasnodar. Регистрируйся для добавления в чат клуба.\s
            ✅ Общение с единомышленниками\s
            ✅ Поддержка в любых технических вопросах\s
            ✅ Клубные встречи\s
            ✅ Скидки у партнеров после получения клубной карты"""),
    //registration messages
    REGISTRATION_HELP_MESSAGE("""
            Для регистрации в клубе нужно будет поделиться:
             📱 карточкой контакта\s
             📅 датой рождения\s
             №  гос. номером авто\s
             🚗 моделью авто\s
             📅 годом выпуска авто"""),
    REQUEST_CONTACT_MESSAGE("Чтобы поделиться контактом нажмите на кнопку внизу ⬇️"),
    SET_USER_NAME_MESSAGE(""), //errors
    WTF_EXCEPTION("Непредвиденная ошибка. Обратитесь к @antonov_krd");

    private final String message;
}
