package core.fu4sbackend.dto;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private UserStatus status;
//    private ArrayList<Notification> notifications;
//    private ArrayList<Comment> comments;
//    private ArrayList<Post> posts;
//    private ArrayList<TestResult> testResults;
}
