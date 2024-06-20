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

    @Test
    void testCredentials() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("");
        loginDTO.setPassword("password123");
        assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
    }

    @Test
    void testCredentials2() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(null);
        loginDTO.setPassword("pasword123");
        assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
    }

    @Test
    void testCredentials3() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user01");
        loginDTO.setPassword("");
        assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
    }

    @Test
    void testCredentials4() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user01");
        loginDTO.setPassword(null);
        assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
    }

    @Test
    void testSignUp() {
        RegisterDTO registerDTO = new RegisterDTO("user01","pasword123","First","Last","email");
        assertThrows(Exception.class,()->authenticationService.signup(registerDTO));
    }


    @Test
    void testSignUp2() {
        RegisterDTO registerDTO = new RegisterDTO("","pasword123","First","Last","email");
        assertThrows(Exception.class,()->authenticationService.signup(registerDTO));
    }

    @Test
    void testSignUp3() {
        RegisterDTO registerDTO = new RegisterDTO(null,"pasword123","First","Last","email");
        assertThrows(Exception.class,()->authenticationService.signup(registerDTO));
    }
    @Test
    void testSignUp4() {
        RegisterDTO registerDTO = new RegisterDTO("user01","","First","Last","email");
        assertThrows(Exception.class,()->authenticationService.signup(registerDTO));
    }
    @Test
    void testSignUp5() {
        RegisterDTO registerDTO = new RegisterDTO("user01",null,"First","Last","email");
        assertThrows(Exception.class,()->authenticationService.signup(registerDTO));
    }
}
