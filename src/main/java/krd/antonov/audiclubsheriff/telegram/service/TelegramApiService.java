package krd.antonov.audiclubsheriff.telegram.service;

import krd.antonov.audiclubsheriff.exceptions.TelegramCreateChatLinkException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendMessageException;
import krd.antonov.audiclubsheriff.exceptions.TelegramSendPhotoException;
import krd.antonov.audiclubsheriff.telegram.configuration.BotPropertiesConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

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
                    MessageFormat.format("{0}bot{1}/sendMessage", url, botToken),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (Exception e) {
            throw new TelegramSendMessageException(sendMessage.getChatId(), e);
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

    public String createChatInviteLink(String chatId) throws TelegramCreateChatLinkException {
        String inviteLink;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CreateChatInviteLink chatInviteLink = new CreateChatInviteLink();
        chatInviteLink.setChatId(chatId);
        chatInviteLink.setMemberLimit(1);
        HttpEntity<CreateChatInviteLink> requestEntity = new HttpEntity<>(chatInviteLink, headers);
        try {
            inviteLink = new CreateChatInviteLink().deserializeResponse(restTemplate.exchange(
                    MessageFormat.format("{0}bot{1}/createChatInviteLink",
                            url, botToken),
                    HttpMethod.POST,
                    requestEntity,
                    String.class).getBody()).getInviteLink();
        } catch (TelegramApiRequestException exception) {
            throw new TelegramCreateChatLinkException(chatId, exception);
        }
        return inviteLink;
    }
}
