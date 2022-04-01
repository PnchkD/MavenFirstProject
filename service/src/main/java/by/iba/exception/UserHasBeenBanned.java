package by.iba.exception;

import org.springframework.security.authentication.AccountStatusException;

public class UserHasBeenBanned extends AccountStatusException {

    public UserHasBeenBanned(String msg) {
        super(msg);
    }

}
