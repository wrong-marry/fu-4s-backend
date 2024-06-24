package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDto {
    private Integer id;
    private float result;
    private String date;
    private String username;
    private boolean isPersonalized;
    private QuestionSetDto questionSet;
}
