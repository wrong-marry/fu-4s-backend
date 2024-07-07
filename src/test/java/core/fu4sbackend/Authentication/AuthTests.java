package core.fu4sbackend.Authentication;

import core.fu4sbackend.Fu4sBackendApplicationTests;
import core.fu4sbackend.controller.AuthController;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.repository.UserRepository;
import core.fu4sbackend.security.AuthenticationService;
import core.fu4sbackend.security.LoginDTO;
import core.fu4sbackend.security.RegisterDTO;
import core.fu4sbackend.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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

    @ParameterizedTest
    @CsvSource({"user32,passwo,First,Last,test@gmail.com, NORMAL",
            "user1,passwo,First,Last,test@gmail.com, EXCEPTION",
            "user11,passwo,First,Last,test@gmail.com, EXCEPTION",
            ",passwo,First,Last,test@gmail.com, EXCEPTION",
            "user11,,First,Last,test@gmail.com, EXCEPTION",
            "user11,passw,First,Last,test@gmail.com, EXCEPTION",
            "user11,passwo,'',Last,test@gmail.com, EXCEPTION",
            "user11,passwo,,Last,test@gmail.com, EXCEPTION",
            "user11,passwo,'',Last,test@gmail.com, EXCEPTION",
            "user11,passwo,,Last,test@gmail.com, EXCEPTION",
            "user11,passwo,First,Last,@gmail.com, EXCEPTION",
            "user11,passwo,First,Last,test@gmail., EXCEPTION",
            "user11,passwo,First,Last,, EXCEPTION",
    })
    void testAuthentication(String username, String password, String firstname, String lastname, String email, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            userRepository.deleteByUsername(username);
            RegisterDTO registerDTO = new RegisterDTO(username,password,firstname,lastname,email);
            JSONObject registerResponse = authenticationService.signup(registerDTO);
            UserDto u = userService. getByUsername(registerResponse.getString(username));
            assert (u.getFirstName().equals(registerDTO.getFirstName())&&u.getEmail().equals(registerDTO.getEmail()));
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(registerDTO.getUsername());
            loginDTO.setPassword(registerDTO.getPassword());
            assert(authController.authenticate(loginDTO).getStatusCode()== HttpStatus.OK);
        }
        else Assertions.assertThrows(Exception.class, ()->{
            RegisterDTO registerDTO = new RegisterDTO(username,password,firstname,lastname,email);
            authenticationService.signup(registerDTO);
        });
    }

    @ParameterizedTest
    @CsvSource({"user11,password123, NORMAL",
            "user-11,password123, EXCEPTION",
            ",password123, EXCEPTION",
            "user11,passw, EXCEPTION",
            "user11,null, EXCEPTION"})
    void testCredentials(String username, String password, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
    }

}
