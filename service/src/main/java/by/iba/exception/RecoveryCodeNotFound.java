package by.iba.exception;

public class RecoveryCodeNotFound extends RuntimeException {

    public RecoveryCodeNotFound() {
        super("Recovery code has been hot found.");
    }

}