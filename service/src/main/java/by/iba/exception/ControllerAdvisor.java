package by.iba.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class ControllerAdvisor {

    private final MessageSource messageSource;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User not found");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsAlreadyExistException.class)
    public ResponseEntity<Object> handleUserIsAlreadyExistException(
            UserIsAlreadyExistException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User is already exist");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecoveryCodeNotFoundException.class)
    public ResponseEntity<Object> handleRecoveryCodeNotFoundException(
            RecoveryCodeNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Recovery code has been hot found");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserHasBeenBannedException.class)
    public ResponseEntity<Object> handleUserHasBeenBannedException(
            UserHasBeenBannedException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User account is disabled");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<Object> handleUserRoleNotFoundException(
            UserRoleNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User role has been hot found");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBanException.class)
    public ResponseEntity<Object> handleInvalidBanException(
            InvalidBanException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Admin cannot ban himself");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Bad credentials");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailParseException.class)
    public ResponseEntity<Object> handleAddressException(
            MailParseException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Illegal email address");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Object> handleMessagingException(
            MessagingException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Invalid Addresses");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorWrapper<ErrorMessage>> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, ErrorMessage> errorsMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
                    if (errorsMap.containsKey(error.getField())) {
                        errorsMap.put(error.getField(), new ErrorMessage(400, error.getField(),
                                error.getCode()));
                    } else {
                        errorsMap.put(error.getField(), new ErrorMessage(400, error.getField(),
                                error.getCode()));
                    }
                }
        );
        List<ErrorMessage> errors = new ArrayList<>(errorsMap.values());
        return new ResponseEntity<>(
                new ErrorWrapper<>(errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> processError(Exception e, Locale locale) {
        /*
            500
         */
        e.printStackTrace();

        final String localizedMessage = getLocalizedMessage("exception.server_error", locale);

        return
                new ResponseEntity<>(
                        new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "server_error",
                                localizedMessage),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getLocalizedMessage(final String error, final Locale locale) {

        return messageSource.getMessage(error, (Object[]) null, locale);
    }

}
