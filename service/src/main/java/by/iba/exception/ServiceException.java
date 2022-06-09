package by.iba.exception;

public class ServiceException extends AbstractException {

    public ServiceException(Integer httpCode, String error) {
        super(httpCode, error, "service_error");
    }

    public ServiceException(Integer httpCode, String error, Throwable cause) {
        super(httpCode, error, "service_error", cause);
    }

    public ServiceException(Integer httpCode, String error, String errorDescription) {
        super(httpCode, error, errorDescription);
    }

}

