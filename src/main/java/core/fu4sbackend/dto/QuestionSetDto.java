package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestionSetDto extends PostDto{
    private int attempts;
    private List<QuestionDto> questions;
//    private ArrayList<TestResultDto> results;
}
