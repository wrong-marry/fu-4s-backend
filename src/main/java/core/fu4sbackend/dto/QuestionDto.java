package core.fu4sbackend.dto;

import core.fu4sbackend.entity.QuestionSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Integer id;
    private String content;
    private QuestionSet questionSet;
    private List<AnswerDto> answers;
}
