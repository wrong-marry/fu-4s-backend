package core.fu4sbackend.controller;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guest")
public class GuestController {
    private final UserService userService;
    @Autowired
    public GuestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/get-user-by-username")
    public ResponseEntity<UserDto> getByUsername(@RequestParam String username){
        return new ResponseEntity<>(
        userService.getByUsername(username), HttpStatus.OK);
    }
}

