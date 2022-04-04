package krd.antonov.audiclubsheriff.exceptions;

import java.text.MessageFormat;

public class TelegramForwardMessageException extends Exception {

    private static final String MESSAGE = "Error forward message-{0} from chat-{1}  to chat-{2} ";

    public TelegramForwardMessageException(String chatId, String fromChatId, int messageId, Throwable cause) {
        super(MessageFormat.format(MESSAGE, messageId, fromChatId, chatId), cause);
    }
}
