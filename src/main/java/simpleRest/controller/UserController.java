package simpleRest.controller;

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
        HttpServletResponse response, HttpServletRequest request) {

        final long start = System.currentTimeMillis();
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            userService.authentication(username, password, response, 2L);

        } else {
            Map<String, String> cookies = cookieService.getCookies(request);
            userService
                .authentication(cookies.get("username"), cookies.get("password"), response, 2L);
        }
        final long executionTime = System.currentTimeMillis() - start;
        logger.info(
            "UID: " + UUID.randomUUID().toString() + "; response time: " + executionTime + "ms;");
        return "hello user";
    }


}
