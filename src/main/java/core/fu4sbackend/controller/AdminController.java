
package core.fu4sbackend.controller;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.service.SubjectService;
import core.fu4sbackend.service.UserService;
import org.json.JSONObject;
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
    private final SubjectService subjectService;

    @Autowired
    public AdminController(UserService userSer, SubjectService subjectService) {
        this.userSer = userSer;
        this.subjectService = subjectService;
    }

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUsers(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize){
        --pageNum;
        List<UserDto> userDtoList = userSer.getAllUsers(pageNum, pageSize);
        return userDtoList;
    }

    @GetMapping("/getNumUser")
    public ResponseEntity<Integer> getNumberOfUsers(){
        return ResponseEntity.ok(userSer.getNumberOfUsers());
    }

    @GetMapping("/getAllByUserRole")
    public List<UserDto> getAllByUserRole(@RequestParam UserRole userrole,
                                          @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        --pageNum;
        List<UserDto> userDtoList = userSer.getAllByUserRole(userrole,pageNum, pageSize);
        return userDtoList;
    }
    @GetMapping("/getNumEachRole")
    public ResponseEntity<Integer> getNumUsesEachRole(@RequestParam UserRole userrole){
        return ResponseEntity.ok(userSer.getNumberOfUserByRole(userrole));
    }

    @PutMapping("/disableSubject")
    public ResponseEntity<Void> disableSubject(@RequestParam("subjectCode") String subjectCode) {
        try {
            subjectService.disableSubject(subjectCode);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/banUser")
    public ResponseEntity<String> banUser(@RequestParam("username") String username ) {
        JSONObject jsonObject = new JSONObject();
        try {
            userSer.banUser(username);
            jsonObject.put("message", "Banned user successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/activateUser")
    public ResponseEntity<String> activeUser(@RequestParam("username") String username) {
        JSONObject jsonObject = new JSONObject();
        try {
            userSer.activateUser(username);
            jsonObject.put("message", "Active successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

