package core.fu4sbackend.controller;

import core.fu4sbackend.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
//@Secured("STAFF")
public class StaffController {
    private final PostService postService;

    public StaffController(PostService postService) {
        this.postService = postService;
    }


}
