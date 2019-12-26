package simpleRest.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simpleRest.exception.UserForbiddenException;
import simpleRest.service.UserService;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);
    private final UserService userService;

    @GetMapping("/admin")
    public String getAdmin(@RequestParam(required = false) String username,
        @RequestParam(required = false) String password, HttpServletResponse response)
        throws IOException {
        final long start = System.currentTimeMillis();

        if (username != null && password != null && !username.isEmpty() && !password
            .isEmpty()) {
            try {
                userService.userAuthentication(username, password, "ADMIN");
            } catch (UserForbiddenException ex) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        final long executionTime = System.currentTimeMillis() - start;
        logger.info("response time: " + executionTime + "ms;");
        return RandomStringUtils.randomAlphabetic(15);
    }
}
