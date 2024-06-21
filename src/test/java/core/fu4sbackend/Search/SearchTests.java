package core.fu4sbackend.Search;

import core.fu4sbackend.controller.SearchController;
import core.fu4sbackend.dto.SearchRequest;
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

    @ParameterizedTest
    @CsvSource({})
    void testSearch(String keyword, String subjectCode, Integer semester, String postTime, String isTest, SearchRequest.SearchOrder order, Integer pageSize, Integer page) {
        ResponseEntity<String> er = searchController.searchPost(keyword,subjectCode, semester,
                postTime,isTest, null, order,pageSize,page);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
       }

}
