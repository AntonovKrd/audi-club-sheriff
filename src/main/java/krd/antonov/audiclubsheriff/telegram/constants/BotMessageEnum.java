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
    //Неожиданные ошибки
    WTF_EXCEPTION("Непредвиденная ошибка. Обратитесь к @antonov_krd !");

    private final String message;
}
