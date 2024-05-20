package core.fu4sbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class QuestionSet extends Post {
    private int attempts;

    @OneToMany(mappedBy = "questionSet", cascade = CascadeType.ALL)
    private ArrayList<Question> questions;

    @OneToMany(mappedBy = "questionSet", cascade = CascadeType.ALL)
    private ArrayList<TestResult> results;
}
