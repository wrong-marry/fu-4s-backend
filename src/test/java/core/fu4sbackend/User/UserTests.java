package core.fu4sbackend.User;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.controller.StaffController;
import core.fu4sbackend.controller.UserController;
import core.fu4sbackend.dto.UserDto;
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
    @CsvSource({})
    void changeUserInfo(String username, String email) {
        UserDto userDto = userController.getByUsername("user02").getBody();
        userDto.setEmail("testtt");
        userController.editEmailFirstNameLastName(userDto, "user02");
        assert (userController.getByUsername("user02").getBody().getEmail().equals("testtt")&&userController.compareOldAndNewPassWord("user02", "testtt"));
    }

    @ParameterizedTest
    @CsvSource({})
    void testChangePassword(String username, String newPassword) {
        userController.changePassword(username,newPassword);
    }
}
