package simpleRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Bad credentials")
public class UserForbiddenException extends Exception {

    public UserForbiddenException() {
        super("User forbidden");
    }
}
