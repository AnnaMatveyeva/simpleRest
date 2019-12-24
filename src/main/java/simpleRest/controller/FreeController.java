package simpleRest.controller;

import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreeController {

    private static final Logger logger = Logger.getLogger(FreeController.class.getName());

    @GetMapping("/free")
    public String getFree() {
        final long start = System.currentTimeMillis();
        final long executionTime = System.currentTimeMillis() - start;
        logger.info(
            "UID: " + UUID.randomUUID().toString() + "; response time: " + executionTime + "ms;");
        return "It is for free"; //add string generator
    }
}
