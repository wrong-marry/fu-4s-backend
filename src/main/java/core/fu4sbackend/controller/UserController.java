package core.fu4sbackend.controller;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<UserDto> getByUsername(@RequestParam("username") String username){
        return new ResponseEntity<>(
        userService.getByUsername(username), HttpStatus.OK);
    }
    @PutMapping("/edit-profile")
    public UserDto editEmailFirstNameLastName(@RequestBody UserDto userDto,@RequestParam String username){
        return userService.editEmailFirstNameLastName(userDto,username);
    }
}
