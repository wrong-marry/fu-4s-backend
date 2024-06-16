package core.fu4sbackend.User;

import core.fu4sbackend.controller.UserController;
import core.fu4sbackend.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
    @Autowired
    UserController userController;


    @Test
    void changeUserInfo() {
        UserDto userDto = userController.getByUsername("user02").getBody();
        userDto.setEmail("testtt");
        userController.changePassword("user02", "testtt");
        userController.editEmailFirstNameLastName(userDto, "user02");
        assert (userController.getByUsername("user02").getBody().getEmail().equals("testtt")&&userController.compareOldAndNewPassWord("user02", "testtt"));
        userController.changePassword("user02", "password123");
    }
}
