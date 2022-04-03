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
             🚗 моделью авто\s
             📅 годом выпуска авто\s
             №  гос. номером авто"""),
    REQUEST_USER_CONTACT_MESSAGE("Чтобы поделиться контактом жми на кнопку внизу ⬇️"),
    REQUEST_USER_NAME_MESSAGE("Записал, теперь напиши настоящее имя 👥"),
    INVALID_USER_NAME_MESSAGE("Не очень похоже на имя, нужно настоящее имя 🙂"),
    REQUEST_USER_DATE_BIRTH_MESSAGE("""
            Отлично! Теперь дата рождения 📅 чтобы знали когда поздравлять😎\s
            Допустимый возраст от 18 до 80 лет\s
            формат ввода DD.MM.YYYY\s
            (пример - 31.12.1980)"""),
    INVALID_USER_DATE_BIRTH_MESSAGE("""
            Не получилось распознать дату\s
            давай еще раз 🔄\s
            Допустимый возраст от 18 до 80 лет\s
            формат ввода dd.MM.yyyy\s
            (пример - 31.12.1980)"""),
    REQUEST_VEHICLE_MODEL_MESSAGE("""
            Теперь о машине 🚗 Если их несколько, то сначала добавь любую.\s
            Остальные можно будет добавить позже по желанию.\s
            Какая у тебя модель?\s
            Только латинские буквы с цифрами, 2-25 символов\s
            Примеры: RSQ8, A6 allroad quattro"""),
    INVALID_VEHICLE_MODEL_MESSAGE("""
            Не знаю такой модели\s
            Нужны латинские буквы с цифрами, 2-25 символов\s
            Примеры: RSQ8, A6 allroad quattro\s
            Напиши еще раз 🔄"""),
    REQUEST_VEHICLE_YEAR_MESSAGE("""
            Крутая тачка!🔥 Какого года выпуска? 📅\s
            формат ввода yyyy (пример - 2018)"""),
    INVALID_VEHICLE_YEAR_MESSAGE("""
            Что-то не так, попробуй еще раз 🔄\s
            формат ввода yyyy\s
            (пример - 1998, 2008)"""),
    REQUEST_VEHICLE_LICENSE_PLATE_MESSAGE("""
            Почти у цели, остался последний шаг 🎯\s
            Напиши полный гос номер\s
            ‼️Должен состоять из букв и цифр\s
            ‼️Допустимая длина 8-9 символов"""),
    INVALID_VEHICLE_LICENSE_PLATE_MESSAGE("""
            Номер не распознан ❌\s
            ‼️Должен состоять из букв и цифр\s
            ‼️Допустимая длина 8-9 символов\s
            Введи корректный номер 🔄"""),
    REGISTRATION_SAVE_STAGE_MESSAGE("Сохраняю все данные, нужно немного времени ⌚️"),
    REGISTRATION_SUCCESS_MESSAGE("""
            Сохранил, все в порядке ✅\s
            После проверки заявки на регистрацию владельцем клуба я сообщу о результате и
            если все хорошо, автоматически добавлю в чат клуба!"""),
    //errors
    EXCEPTION_ILLEGAL_MESSAGE("К такому я не готов! Пока что работаю только с текстом и карточками контактов \uD83D\uDE30\uD83D\uDE30\uD83D\uDE30"),
    WTF_EXCEPTION("Непредвиденная ошибка. Обратитесь к @antonov_krd"),
    //temp
    NOT_FOUND_FUNCTIONAL("Не понимаю что мне с этим делать \uD83D\uDE30 воспользуйтесь меню ниже!"),
    NOT_READY_FUNCTIONAL("Данный функционал в стадии разработки 💻");
    private final String message;
}
