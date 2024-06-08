package core.fu4sbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


class QuestionPriorityId implements Serializable {
    private User user;
    private Question question;

    public QuestionPriorityId(Question question, User user) {
        this.question = question;
        this.user = user;
    }
}
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@IdClass(QuestionPriorityId.class)
public class QuestionPriority {
    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private int priority;
}
