package krd.antonov.audiclubsheriff.exceptions;

import java.text.MessageFormat;

public class TelegramSendPhotoException extends Exception {

    private static final String MESSAGE = "Error send fileId-{0} in chat-{1} ";

    public TelegramSendPhotoException(String fileId, String chatId, Throwable cause) {
        super(MessageFormat.format(MESSAGE, fileId, chatId), cause);
    }
}
