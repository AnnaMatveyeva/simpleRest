package simpleRest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreeController {

    @GetMapping("/free")
    public String getFree(){
        return "It is for free";
    }
}
