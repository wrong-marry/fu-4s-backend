package core.fu4sbackend.controller;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<UserDto> getByUsername(@RequestParam String username){
        return new ResponseEntity<>(
        userService.getByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/compare-password")
    public boolean compareOldAndNewPassWord(@RequestParam String username,
                                            @RequestParam String confirmPassword){
        return userService.compareOldAndNewPassword(username,confirmPassword);
    }
    @PutMapping("/edit-profile")
    public UserDto editEmailFirstNameLastName(@RequestBody UserDto userDto,@RequestParam String username){
        return userService.editEmailFirstNameLastName(userDto,username);
    }
    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return userDtoList;
    }
    @PutMapping("/change-password")
    public UserDto changePassword(@RequestParam String username,@RequestParam String newPassword)
    {
        return userService.changePassword(newPassword,username);
    }
}

