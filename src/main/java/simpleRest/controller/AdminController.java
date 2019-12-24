package simpleRest.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simpleRest.service.UserService;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;
    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @GetMapping("/admin")
    public String getAdmin(@RequestParam(required = false) String username,
        @RequestParam(required = false) String password, HttpServletResponse response) {
        final long start = System.currentTimeMillis();
        if (username != null && password != null) {
            userService.authentication(username, password, response, 2L);
        } else {
            try {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final long executionTime = System.currentTimeMillis() - start;
        logger.info("response time: " + executionTime + "ms;");
        return "hello" + username;
    }
}
