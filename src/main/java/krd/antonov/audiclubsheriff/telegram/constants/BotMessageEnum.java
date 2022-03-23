package krd.antonov.audiclubsheriff.telegram.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotMessageEnum {
    //start message
    START_MESSAGE("""
            Привет! Это бот #audiclubkrasnodar. Владелец Audi? Регистрируйся для добавления в чат клуба.\s
            ✅ Поддержка в любых вопросах по машине\s
            ✅ Клубные встречи\s
            ✅ Скидки у партнеров при наличии клубной карты"""),
    //Неожиданные ошибки
    WTF_EXCEPTION("Непредвиденная ошибка. Обратитесь к разработчику!");

    private final String message;
}
