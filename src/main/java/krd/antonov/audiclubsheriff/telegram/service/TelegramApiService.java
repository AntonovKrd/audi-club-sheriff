package krd.antonov.audiclubsheriff.telegram.service;

import krd.antonov.audiclubsheriff.exceptions.TelegramForwardMessageException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendPhotoException;
import krd.antonov.audiclubsheriff.telegram.configuration.BotPropertiesConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

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

    public void forwardMessage(ForwardMessage forwardMessage) throws TelegramForwardMessageException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ForwardMessage> requestEntity = new HttpEntity<>(forwardMessage, headers);
        try {
            restTemplate.exchange(
                    MessageFormat.format("{0}bot{1}/forwardMessage?chat_id={2}&from_chat_id={3}&message_id={4}",
                            url, botToken, forwardMessage.getChatId(), forwardMessage.getFromChatId(), forwardMessage.getMessageId()),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (Exception e) {
            throw new TelegramForwardMessageException(forwardMessage.getChatId(),
                    forwardMessage.getFromChatId(), forwardMessage.getMessageId(), e);
        }
    }

    public void sendPhoto(String chatId, String fileId, String caption) throws TelegramSendPhotoException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SendPhoto> requestEntity = new HttpEntity<>(new SendPhoto(), headers);
        try {
            restTemplate.exchange(
                    MessageFormat.format("{0}bot{1}/sendPhoto?chat_id={2}&photo={3}&caption={4}",
                            url, botToken, chatId, fileId, caption),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (Exception e) {
            throw new TelegramSendPhotoException(fileId, chatId, e);
        }
    }
}
