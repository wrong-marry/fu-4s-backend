package core.fu4sbackend.User;

import core.fu4sbackend.Fu4sBackendApplicationTests;
import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.controller.StaffController;
import core.fu4sbackend.controller.UserController;
import core.fu4sbackend.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
    @Autowired
    UserController userController;

    @ParameterizedTest
    @CsvSource({"user06,hai@gmail.com,NORMAL",
            "user-6,hai@gmail.com,EXCEPTION",
            "user06,@gmail.com,EXCEPTION",
            "user06,hai@gmail.,EXCEPTION",
            "user06,,EXCEPTION",
            ",hai@gmail.com,EXCEPTION",})
    void changeUserInfo(String username, String email, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            UserDto userDto = userController.getByUsername(username).getBody();
            userDto.setEmail(email);
            assert userController.editEmailFirstNameLastName(userDto, "user06")!=null;
        }
        else {
            Assertions.assertThrows(Exception.class,()-> {
                UserDto userDto = userController.getByUsername(username).getBody();
                userDto.setEmail(email);
                userController.editEmailFirstNameLastName(userDto, "user02");
            });
        }
    }

    @ParameterizedTest
    @CsvSource({"user11,passwo,NORMAL",
            "user1,passwo,EXCEPTION",
            "user11,passw,EXCEPTION",
            "user11,,EXCEPTION",
            ",passwo,EXCEPTION"})
    void testChangePassword(String username, String newPassword, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        assert userController.changePassword(username,newPassword)!=null;
        else Assertions.assertThrows(Exception.class, ()->userController.changePassword(username,newPassword));
    }
}
