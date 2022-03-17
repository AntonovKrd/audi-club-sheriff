package krd.antonov.audiclubsheriff.exceptions;

public class UserNotFoundException extends Exception {

    private static final String MESSAGE = "User %s not found";

    public UserNotFoundException(String id) {
        super(String.format(MESSAGE, id));
    }
}
