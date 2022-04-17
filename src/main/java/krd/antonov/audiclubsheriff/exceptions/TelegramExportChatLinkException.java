package krd.antonov.audiclubsheriff.exceptions;

public class TelegramExportChatLinkException extends Exception {

    private static final String MESSAGE = "Error export chat invite link with chatId - %s";

    public TelegramExportChatLinkException(String chatId, Throwable cause) {
        super(String.format(MESSAGE, chatId), cause);
    }
}
