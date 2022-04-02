package by.iba.exception;

public class UserIsAlreadyExistException extends RuntimeException {

    public UserIsAlreadyExistException() {
        super("USER_IS_ALREADY_EXIST");
    }
}
