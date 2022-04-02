package by.iba.exception;

import org.springframework.security.authentication.AccountStatusException;

public class UserHasBeenBannedException extends AccountStatusException {

    public UserHasBeenBannedException(String msg) {
        super(msg);
    }

}
