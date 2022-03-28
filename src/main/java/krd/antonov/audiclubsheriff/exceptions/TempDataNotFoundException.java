package krd.antonov.audiclubsheriff.exceptions;

public class TempDataNotFoundException extends Exception {

    private static final String MESSAGE = "TempData with chatId - %s not found";

    public TempDataNotFoundException(String chatId) {
        super(String.format(MESSAGE, chatId));
    }
}
