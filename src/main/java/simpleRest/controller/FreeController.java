package simpleRest.controller;

import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import simpleRest.util.StringGenerator;

@RestController
public class FreeController {

    private static final Logger logger = Logger.getLogger(FreeController.class.getName());

    @GetMapping("/free")
    public String getFree() {
        final long start = System.currentTimeMillis();
        final long executionTime = System.currentTimeMillis() - start;
        logger.info(
            "UID: " + UUID.randomUUID() + "; response time: " + executionTime + "ms;");
        return StringGenerator.getRandomString(); //add string generator
    }
}
