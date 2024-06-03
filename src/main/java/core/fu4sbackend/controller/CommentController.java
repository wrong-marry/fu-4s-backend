package core.fu4sbackend.controller;

import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.service.CommentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsInAPost(@PathVariable int id,
                                                               @RequestParam(required = false) String offset,
                                                               @RequestParam(required = false) String isStaff,
                                                               @RequestParam(required = false) String sorted) {
        return new ResponseEntity<>(
                commentService.findByPostId(
                        id,
                        offset == null || offset.trim().matches("[1-9]+") ? 0 : Integer.parseInt(offset),
                        Boolean.valueOf(isStaff),
                        sorted != null),
                HttpStatus.OK);
    }

    @PostMapping("/upload/post-{id}")
    public ResponseEntity<String> uploadComment(@PathVariable int id, @RequestBody CommentDto commentDto) {
        JSONObject jsonObject = new JSONObject();
        switch (commentService.save(commentDto, id)) {
            case -1:
                jsonObject.put("message", "Invalid username");
                //DEBUG
                System.out.println("Invalid username " + commentDto.getUsername());
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CONFLICT);
            case -2:
                jsonObject.put("message", "Invalid post id");
                //DEBUG
                System.out.println("Invalid post id " + commentDto.getId());
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CONFLICT);
            default:
                jsonObject.put("message", "Successfully uploaded comment");
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        }
    }

    //OWNER ONLY
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable int id, @RequestBody CommentDto commentDto) {
        JSONObject jsonObject = new JSONObject();
        String message = switch (commentService.update(id, commentDto.getContent())) {
            case -1:
                yield "Invalid comment id";
            case 0:
                yield "Successfully updated comment";
            default:
                yield "Something went wrong";
        };
        jsonObject.put("message", message);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    //OWNER ONLY
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
        JSONObject jsonObject = new JSONObject();

        if (commentService.delete(id) == 0) {
            jsonObject.put("message", "Deleted comment");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } else {
            jsonObject.put("message", "Something went wrong");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CONFLICT);
        }
    }

    //STAFF OR ADMIN ONLY
    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateCommentStatus(@PathVariable int id) {
        JSONObject jsonObject = new JSONObject();
        return switch (commentService.updateStatus(id)) {
            case -1 -> {
                jsonObject.put("message", "Invalid comment id");
                //DEBUG
                System.out.println("Comment id: " + id);
                yield new ResponseEntity<>(jsonObject.toString(), HttpStatus.CONFLICT);
            }
            case 0 -> {
                jsonObject.put("message", "Successfully updated status");
                yield new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
            }
            default -> {
                jsonObject.put("message", "Something went wrong");
                yield new ResponseEntity<>(jsonObject.toString(), HttpStatus.CONFLICT);
            }
        };
    }
}
