package core.fu4sbackend.Search;

import core.fu4sbackend.Fu4sBackendApplicationTests;
import core.fu4sbackend.controller.SearchController;
import core.fu4sbackend.dto.SearchRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
public class SearchTests {

    @Autowired
    private SearchController searchController;
//if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
    @ParameterizedTest
    @CsvSource({"a,1,swp,2023-03-24T23:00:00 00:00Z,1,1,NORMAL",
            "'',9,'',,,1,EXCEPTION",
            ",0,,2023-03-24T23:00:00 00:00Z,,1,EXCEPTION",
            ",10,swp,2023-03-24T23:00:00 00:00Z,1,1,ABNORMAL_RESPONSE",
            "a,1,swp,2023-02-29T23:00:00 00:00Z,1,1,ABNORMAL_RESPONSE",
            "a,1,swp,2023-03-24T23:00:00 00:00Z,0,1,ABNORMAL_RESPONSE",
            "a,1,swp,2023-03-24T23:00:00 00:00Z,1,0,EXCEPTION",
            "a,1,swp,2023-03-24T23:00:00 00:00Z,1,,ABNORMAL_RESPONSE",
            "a,1,swp,2023-03-24T23:00:00 00:00Z,1,-1,EXCEPTION",
            "a,1,swp,2023-03-24T23:00:00 00:00Z,'1',-3,EXCEPTION",
            "a,1,swp,2023-03-24T23:00:00 00:00Z,1,'-1',EXCEPTION",
            "a,1,swp,'',1,1,ABNORMAL_RESPONSE"})
    void testSearch(String keyword, Integer semester, String subjectCode, String postTime,
                    Integer pageSize, Integer page, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
        if (expectedTestResult == Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            ResponseEntity<String> er = searchController.searchPost(keyword, subjectCode, semester,
                    postTime, null, null, null, pageSize, page);
            assert (er.getStatusCode() == HttpStatus.OK && er.getBody() != null && er.getBody().length() > 21);
        }
        else if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.EXCEPTION) {
            Assertions.assertThrows(Exception.class, ()->searchController.searchPost(keyword, subjectCode, semester,
                    postTime, null, null, null, pageSize, page));
        }
        else
        {
            ResponseEntity<String> er = searchController.searchPost(keyword, subjectCode, semester,
                    postTime, null, null, null, pageSize, page);
            assert (er.getStatusCode() != HttpStatus.OK);
        }
    }

    @ParameterizedTest
    @CsvSource({"","DATE_ASC","TITLE_ASC","TITLE_DESC"})
    void testOrder(SearchRequest.SearchOrder order) {
        assert searchController.searchPost(null,null,
                null,null,null,null,
                order, 3,1).getStatusCode()==HttpStatus.OK;
    }
}
