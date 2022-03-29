package by.iba.exception;

public class UserHasBeenBanned extends RuntimeException {
    public UserHasBeenBanned() {
        super("User has been banned.");
    }
}
