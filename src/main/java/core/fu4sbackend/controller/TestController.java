package core.fu4sbackend.controller;

import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.service.PostService;
import core.fu4sbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TestController {
    private final UserService userService;
    private final PostService postService;

    public TestController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/user")
    public String test() {
        return "hello from user";
    }

    @Secured("ADMIN")
    @GetMapping("/admin")
    public String test2() {
        return "hello from admin";
    }

    @GetMapping("/v1/users/profile/{username}")
    public ResponseEntity<UserDto> test3(@PathVariable("username") String username) {
        UserDto u = userService.getByUsername(username);
        return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/v1/getRecentPost")
    public ResponseEntity<List<PostDto>> getRecentPost() {
        SearchRequest sr = new SearchRequest(null,null,null,null,null);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }
}
