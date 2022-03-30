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
             👥 настоящим именем\s
             📅 датой рождения\s
             №  гос. номером авто\s
             🚗 модель авто\s
             📅 годом выпуска авто"""),
    REQUEST_CONTACT_MESSAGE("Чтобы поделиться контактом нажмите на кнопку внизу ⬇️"),
    REQUEST_USER_NAME_MESSAGE("Записал, теперь напишите настоящее имя 👥"),
    //errors
    EXCEPTION_ILLEGAL_MESSAGE("К такому я не готов! Пока что работаю только с текстом и карточками контактов \uD83D\uDE30\uD83D\uDE30\uD83D\uDE30"),
    WTF_EXCEPTION("Непредвиденная ошибка. Обратитесь к @antonov_krd"),
    //temp
    NOT_READY_FUNCTIONAL("Данный функционал в стадии разработки 💻");
    private final String message;
}
