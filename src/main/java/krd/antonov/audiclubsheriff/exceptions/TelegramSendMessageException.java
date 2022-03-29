package krd.antonov.audiclubsheriff.exceptions;

public class TelegramSendMessageException extends Exception {

    private static final String MESSAGE = "Error send message in chatId - %s ";

    public TelegramSendMessageException(String chatId, Throwable cause) {
        super(chatId, cause);
    }
}
