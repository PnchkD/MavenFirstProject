package by.iba.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User has been hot found.");
    }
}
