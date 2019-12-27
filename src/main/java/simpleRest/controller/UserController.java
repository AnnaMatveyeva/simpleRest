package simpleRest.controller;

import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simpleRest.exception.UserForbiddenException;
import simpleRest.service.CookieService;
import simpleRest.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final CookieService cookieService;
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(UserController.class);

    @GetMapping("/user")
    public String getUser(
        @RequestParam(required = false) @CookieValue(value = "username", defaultValue = "username") String username,
        @RequestParam(required = false) @CookieValue(value = "password", defaultValue = "password") String password,
        HttpServletResponse response, HttpServletRequest request)
        throws UserForbiddenException {

        final long start = System.currentTimeMillis();
        if (StringUtils.isNoneEmpty(username) && StringUtils.isNoneEmpty(password)) {
            String codePass = userService.userAuthentication(username, password, "USER")
                .getPassword();
            cookieService.setCookie(username, codePass, response);
        } else {
            Map<String, String> cookies = cookieService.getCookies(request);
            userService.cookieAuthentication(cookies.get("username"), cookies.get("password"));
        }

        final long executionTime = System.currentTimeMillis() - start;
        logger.info(
            "UID: " + UUID.randomUUID() + "; response time: " + executionTime + "ms;");
        return RandomStringUtils.randomAlphabetic(15);
    }


}
