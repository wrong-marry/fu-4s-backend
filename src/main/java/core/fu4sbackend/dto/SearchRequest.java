package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SearchRequest {
    private String title;
    private String subjectCode;
    private Date postTime;
    private boolean isTest;
}
