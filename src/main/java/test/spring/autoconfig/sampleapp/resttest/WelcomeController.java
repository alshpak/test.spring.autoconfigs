package test.spring.autoconfig.sampleapp.resttest;

import org.springframework.web.bind.annotation.GetMapping;

public class WelcomeController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
