package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SearchRequest {
    private String username;
    private String title;
    private String subjectCode;
    private Date postTime;
    private Boolean isTest;
    private SearchOrder order;
    private Integer pageSize;
    private Integer currentPage;
    private Integer semester;

    public enum SearchOrder {
        USERNAME_ASC, USERNAME_DESC, TITLE_ASC, TITLE_DESC, DATE_ASC, DATE_DESC
    }
}
