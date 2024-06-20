package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CheckedQuestionResult {
    private List<Integer> correctAnswersId;
    private List<Integer> wrongAnswersId;
}
