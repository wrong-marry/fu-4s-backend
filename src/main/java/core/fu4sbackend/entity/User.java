package core.fu4sbackend.entity;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private ArrayList<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private ArrayList<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private ArrayList<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private ArrayList<TestResult> testResults;
}
