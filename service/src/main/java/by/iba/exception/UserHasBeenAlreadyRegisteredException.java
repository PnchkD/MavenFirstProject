package by.iba.exception;

public class UserHasBeenAlreadyRegisteredException extends RuntimeException{
    public UserHasBeenAlreadyRegisteredException() {
        super("USER_HAS_BEEN_ALREADY_REGISTERED");
    }
}
