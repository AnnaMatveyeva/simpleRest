package simpleRest.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import simpleRest.exception.UserForbiddenException;

@ControllerAdvice
public class UserForbiddenExceptionHandler {

    @ExceptionHandler(value = UserForbiddenException.class)
    public void handleError(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

}
