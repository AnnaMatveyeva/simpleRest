package simpleRest.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simpleRest.exception.UserForbiddenException;
import simpleRest.service.CookieService;
import simpleRest.service.UserService;

@RestController
public class UserController {

    @Autowired
    private CookieService cookieService;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping("/user")
    public String getUser(
        @RequestParam(required = false) @CookieValue(value = "username", defaultValue = "username") String username,
        @RequestParam(required = false) @CookieValue(value = "password", defaultValue = "password") String password,
        HttpServletResponse response, HttpServletRequest request) throws IOException {
        final long start = System.currentTimeMillis();
        try {
            if (username != null && password != null && !username.isEmpty() && !password
                .isEmpty()) {
                String codePass = userService.userAuthentication(username, password, "USER")
                    .getPassword();
                cookieService.setCookie(username, codePass, response);
            } else {
                Map<String, String> cookies = cookieService.getCookies(request);
                userService.cookieAuthentication(cookies.get("username"), cookies.get("password"));
            }
        } catch (UserForbiddenException ex) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        final long executionTime = System.currentTimeMillis() - start;
        logger.info(
            "UID: " + UUID.randomUUID().toString() + "; response time: " + executionTime + "ms;");
        return "hello user";
    }


}
