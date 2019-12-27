package simpleRest.controller;

import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreeController {

    private static final Logger logger = Logger.getLogger(FreeController.class);

    @GetMapping("/free")
    public String getFree() {
        final long start = System.currentTimeMillis();
        final long executionTime = System.currentTimeMillis() - start;
        logger.info(
            "UID: " + UUID.randomUUID() + "; response time: " + executionTime + "ms;");
        return RandomStringUtils.randomAlphabetic(15);
    }
}
