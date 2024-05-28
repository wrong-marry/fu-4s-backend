
package core.fu4sbackend.controller;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@Secured("ADMIN")
public class AdminController {
    private final UserService userSer;
    @Autowired
    public AdminController(UserService userSer) {
        this.userSer = userSer;
    }

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtoList = userSer.getAllUsers();
        return userDtoList;
    }

}

