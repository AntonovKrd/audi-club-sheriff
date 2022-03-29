package krd.antonov.audiclubsheriff.telegram.service;

import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.telegram.configuration.BotPropertiesConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.text.MessageFormat;

@Service
public class TelegramApiService {

    private final RestTemplate restTemplate;
    private final String url;
    private final String botToken;

    public TelegramApiService(BotPropertiesConfiguration config) {
        this.restTemplate = new RestTemplate();
        url = config.getApiUrl();
        botToken = config.getBotToken();
    }

    public void sendMessage(SendMessage sendMessage) throws TelegramSendMessageException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SendMessage> requestEntity = new HttpEntity<>(sendMessage, headers);
        try {
            restTemplate.exchange(
                    MessageFormat.format("{0}bot{1}/sendMessage?chat_id={2}", url, botToken, sendMessage.getChatId()),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (Exception e) {
            throw new TelegramSendMessageException(sendMessage.getChatId(), e);
        }
    }
}
