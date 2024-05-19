package core.fu4sbackend.dto;

import core.fu4sbackend.entity.Question;
import core.fu4sbackend.entity.TestResult;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestionSetDto extends PostDto{
    private int attempts;
    private ArrayList<Question> questions;
    private ArrayList<TestResult> results;
}
