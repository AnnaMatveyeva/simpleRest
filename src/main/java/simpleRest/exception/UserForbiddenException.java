package simpleRest.exception;

public class UserForbiddenException extends Exception {

    public UserForbiddenException(String message) {
        super(message);
    }
}
