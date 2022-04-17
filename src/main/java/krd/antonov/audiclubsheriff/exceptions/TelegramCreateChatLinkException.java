package krd.antonov.audiclubsheriff.exceptions;

public class TelegramCreateChatLinkException extends Exception {

    private static final String MESSAGE = "Error create chat invite link with chatId - %s";

    public TelegramCreateChatLinkException(String chatId, Throwable cause) {
        super(String.format(MESSAGE, chatId), cause);
    }
}
