package by.iba.exception;

public class EmailServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailServiceException() {
        super();
    }

    public EmailServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmailServiceException(final String message) {
        super(message);
    }

    public EmailServiceException(final Throwable cause) {
        super(cause);
    }

}
