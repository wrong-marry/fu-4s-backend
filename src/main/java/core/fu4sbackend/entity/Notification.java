package core.fu4sbackend.entity;

import core.fu4sbackend.constant.NotificationMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date time;

    @Enumerated(EnumType.STRING)
    private NotificationMessage message;

    private boolean isSeen;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private Integer postId;
}
