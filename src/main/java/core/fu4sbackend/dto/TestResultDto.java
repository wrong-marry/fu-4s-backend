package core.fu4sbackend.dto;

import core.fu4sbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDto {
    private Integer id;
    private float result;
    private Date date;
    private User user;
//    private QuestionSet questionSet;
}
