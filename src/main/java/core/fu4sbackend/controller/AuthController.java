package core.fu4sbackend.controller;

import core.fu4sbackend.entity.User;
import core.fu4sbackend.security.AuthenticationService;
import core.fu4sbackend.security.LoginDTO;
import core.fu4sbackend.security.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerUserDto) {
        return ResponseEntity.ok(authenticationService.signup(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO input) {
        return ResponseEntity.ok(authenticationService.authenticate(input));
    }
}
