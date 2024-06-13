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
public class QuestionPriorityDTO {
    private int priority;
    private QuestionDto questionDto;
    private UserDto userDto;
}
