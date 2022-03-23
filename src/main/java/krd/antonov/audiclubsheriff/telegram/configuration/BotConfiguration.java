package krd.antonov.audiclubsheriff.telegram.configuration;

import krd.antonov.audiclubsheriff.telegram.AudiSheriffBot;
import krd.antonov.audiclubsheriff.telegram.handlers.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class BotConfiguration {

    private BotPropertiesConfiguration configuration;

    @Bean
    public SetWebhook setWebhook() {
        return SetWebhook.builder().url(configuration.getApiUrl()).build();
    }

    @Bean
    public AudiSheriffBot audiSheriffBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        AudiSheriffBot bot = new AudiSheriffBot(setWebhook, messageHandler);
        bot.setBotPath(configuration.getWebhookPath());
        bot.setBotUsername(configuration.getBotName());
        bot.setBotToken(configuration.getBotToken());
        return bot;
    }
}
