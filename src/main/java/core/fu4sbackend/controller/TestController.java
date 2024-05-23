package core.fu4sbackend.controller;

import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String test() {
        return "hello from user";
    }

    @Secured("ADMIN")
    @GetMapping("/admin")
    public String test2() {
        return "hello from admin";
    }

    @GetMapping("/v1/users/profile/{username}")
    public ResponseEntity<User> test3(@PathVariable("username") String username) {
        User u = userService.getUserByUsername(username);
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }
}
