package simpleRest.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simpleRest.exception.UserForbiddenException;
import simpleRest.service.UserService;
import simpleRest.util.StringGenerator;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

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
        return StringGenerator.getRandomString();
    }
}
