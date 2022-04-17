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
            ✅ Скидки у партнеров после получения клубной карты\s
            -------------------------\s
            Готов?🔥️ Жми кнопку 'Регистрация' ⬇"""),
    //registration messages
    REGISTRATION_HELP_MESSAGE("""
            Для регистрации в клубе нужна информация:
             📱 Карточка контакта\s
             👥 Настоящее имя\s
             📅 Дата рождения\s
             🏙 Город\s
             💼 Род деятельности (не обязательно)\s
             🚗 Модель авто\s
             📅 Год выпуска авто\s
             №  Гос. номер авто\s
             📸 Фото авто с номерами\s
             -----------------------\s
            ❗ Данные не передаются 3-м лицам ❗️️ \s
             ️"""),
    REGISTRATION_REJECTED_MESSAGE("""
            Ты уже зарегистрирован ✅
            Если хочешь изменить данные, жми на кнопку 'Изменить данные'"""),
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
    REQUEST_USER_CITY_MESSAGE("С датой разобрались. Из какого ты города? 🏙"),
    INVALID_USER_CITY_MESSAGE("""
            Не знаю такого города\s
            Попробуй еще раз 🔄"""),
    REQUEST_USER_COMMENT("""
            Отлично, теперь коротко расскажи о своем роде деятельности 💼\s
            Помогать друг другу намного проще когда знаешь кто чем занимается🤝\s
            (формат свободный, если не хочешь указывать, то напиши 'нет')"""),
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
            Осталось немного 🌪\s
            Напиши полный гос номер\s
            ‼️Должен состоять из букв и цифр\s
            ‼️Допустимая длина 8-9 символов"""),
    INVALID_VEHICLE_LICENSE_PLATE_MESSAGE("""
            Номер не распознан ❌\s
            ‼️Должен состоять из букв и цифр\s
            ‼️Допустимая длина 8-9 символов\s
            Введи корректный номер 🔄"""),
    REQUEST_VEHICLE_PHOTO("""
            Почти у цели, остался последний шаг 🎯\s
            Пришли фото своего авто 📸\s
            (не документом, просто фото)"""),
    REGISTRATION_SAVE_STAGE_MESSAGE("Сохраняю все данные, нужно немного времени ⌚️"),
    REGISTRATION_SUCCESS_MESSAGE("""
            Сохранил, все в порядке ✅\s
            После проверки заявки на регистрацию владельцем клуба я сообщу о результате и
            если все хорошо, автоматически добавлю в чат клуба!"""),
    //activation messages
    DECLINE_USER_MESSAGE("""
            Уважаемый, %s\s
            Мы отклонили твою заявку по причине\s
            некорректных данных указанных при регистрации\s
            Твои данные удалены ‼️ \s
            Для регистрации в клубе необходимы\s
            ❗ реальные данные ❗ \s
            Ты можешь повторно пройти регистрацию нажав кнопку внизу ⬇️"""),
    ACCEPT_USER_MESSAGE("""
            Заявка на вступление в клуб одобрена 💥
            Чтобы попасть в чат нажми на кнопку ниже ⬇"""),
    DECLINE_ADMIN_MESSAGE("Заявка пользователя отклонена"),
    ACCEPT_ADMIN_MESSAGE("Заявка пользователя одобрена"),
    //errors
    EXCEPTION_ILLEGAL_MESSAGE("К такому я не готов! Пока что работаю только с текстом и карточками контактов \uD83D\uDE30\uD83D\uDE30\uD83D\uDE30"),
    WTF_EXCEPTION("Непредвиденная ошибка. Обратитесь к @antonov_krd"),
    EXCEPTION_NOT_NOW_PHOTO("В данный момент фото ни к чему, продолжай регистрацию 🙃"),
    //temp
    NOT_FOUND_FUNCTIONAL("Не понимаю что мне с этим делать \uD83D\uDE30 воспользуйтесь меню ниже!"),
    NOT_READY_FUNCTIONAL("Данный функционал в стадии разработки 💻");
    private final String message;
}
