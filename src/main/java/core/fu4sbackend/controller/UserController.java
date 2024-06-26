package core.fu4sbackend.controller;
import core.fu4sbackend.constant.FileConstant;
import core.fu4sbackend.dto.UserAvatarDto;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

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
        if (userDto.getFirstName().length()<6||userDto.getLastName().length()<6
                ||userDto.getEmail().length()<6||!userDto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{1,4}$"))
            throw new InvalidParameterException("Invalid information");
        return userService.editEmailFirstNameLastName(userDto,username);
    }
//    @GetMapping("/getAll")
//    public List<UserDto> getAllUsers(){
//        List<UserDto> userDtoList = userService.getAllUsers();
//        return userDtoList;
//    }
    @PutMapping("/change-password")
    public UserDto changePassword(@RequestParam String username,@RequestParam String newPassword)
    {
        if (newPassword.length()<6) throw new InvalidParameterException("Password too short");
        return userService.changePassword(newPassword,username);
    }

    @PostMapping("/avatar")
    public ResponseEntity<String> getAvatar(@ModelAttribute UserAvatarDto userAvatarDto) throws IOException {
        String realPath = FileConstant.AVATAR_PATH;
        System.out.println(realPath);
        JSONObject jsonObject=new JSONObject();
        try
        {
            FileOutputStream fos = new FileOutputStream(realPath + userAvatarDto.getUsername() + ".jpg");
            fos.write(userAvatarDto.getImage().getBytes());
            fos.close();
            jsonObject.put("message","Successful");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message","Failed");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(path="/getAvatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getAvatar3(@RequestParam String username)  {
        Resource resource1 = new PathResource(FileConstant.AVATAR_PATH + username + ".jpg");
        return ResponseEntity.ok(resource1);
    }
}

