package core.fu4sbackend.Authentication;

import core.fu4sbackend.controller.AuthController;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.repository.UserRepository;
import core.fu4sbackend.security.AuthenticationService;
import core.fu4sbackend.security.LoginDTO;
import core.fu4sbackend.security.RegisterDTO;
import core.fu4sbackend.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AuthTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthController authController;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @Test
    void testAuthentication() {
        userRepository.deleteByUsername("user11");
        RegisterDTO registerDTO = new RegisterDTO("user11","pasword123","First","Last","email");
        JSONObject registerResponse = authenticationService.signup(registerDTO);
        UserDto u = userService. getByUsername(registerResponse.getString("username"));
        assert (u.getFirstName().equals(registerDTO.getFirstName())&&u.getEmail().equals(registerDTO.getEmail()));

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(registerDTO.getUsername());
        loginDTO.setPassword(registerDTO.getPassword()+"False");
        assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
    }

}
