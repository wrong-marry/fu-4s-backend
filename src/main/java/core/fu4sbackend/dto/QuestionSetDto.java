package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestionSetDto extends PostDto{
    private int attempts;
//    private ArrayList<QuestionDto> questions;
//    private ArrayList<TestResultDto> results;
}
