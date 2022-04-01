package by.iba.exception;

public class UserRoleNotFoundException extends RuntimeException {

    public UserRoleNotFoundException() {
        super("User role has been hot found.");
    }

}
