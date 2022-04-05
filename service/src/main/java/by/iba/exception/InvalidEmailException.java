package by.iba.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String errorDescription) {
        super(errorDescription);
    }

}
