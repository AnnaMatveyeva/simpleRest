package simpleRest.exception;

public class UserForbiddenException extends Throwable {

    public UserForbiddenException() {
        super("User forbidden");
    }
}
