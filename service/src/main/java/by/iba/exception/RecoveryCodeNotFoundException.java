package by.iba.exception;

public class RecoveryCodeNotFoundException extends RuntimeException {

    public RecoveryCodeNotFoundException() {
        super("Recovery code has been hot found.");
    }

}