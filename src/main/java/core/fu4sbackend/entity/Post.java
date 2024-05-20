package core.fu4sbackend.entity;

import core.fu4sbackend.constant.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected Date postTime;
    protected String title;
    protected boolean isTest;

    @Enumerated(EnumType.STRING)
    protected PostStatus status;

    @ManyToOne
    @JoinColumn(name = "username")
    protected User user;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    protected Subject subject;
}
