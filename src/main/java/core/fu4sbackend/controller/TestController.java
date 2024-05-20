package core.fu4sbackend.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/user")
    public String test() {
        return "hello from user";
    }

    @Secured("ADMIN")
    @GetMapping("/admin")
    public String test2() {
        return "hello from admin";
    }
}
