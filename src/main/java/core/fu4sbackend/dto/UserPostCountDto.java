package core.fu4sbackend.dto;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import core.fu4sbackend.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostCountDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime enrolledDate ;
    private int postCount;
//    private ArrayList<Notification> notifications;
//    private ArrayList<Comment> comments;
    //private ArrayList<Post> posts;
//    private ArrayList<TestResult> testResults;
}
