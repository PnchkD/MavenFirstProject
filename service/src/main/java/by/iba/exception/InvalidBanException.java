package by.iba.exception;

public class InvalidBanException extends RuntimeException {

    public InvalidBanException() {
        super("Admin cannot ban himself");
    }

}