package core.fu4sbackend.Search;

import core.fu4sbackend.controller.SearchController;
import core.fu4sbackend.dto.SearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class SearchTests {

    @Autowired
    private SearchController searchController;

    @Test
    void testSearch() {
        ResponseEntity<String> er = searchController.searchPost("a","SWP391", 5, "2022-12-30T20:00:00 00:00Z","false", null, SearchRequest.SearchOrder.DATE_ASC,10,null);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
        er = searchController.searchPost(null,null, null, null,"true", null, null,10,1);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
    }

    @Test
    void testNullOrderSearch() {
        ResponseEntity<String> er = searchController.searchPost("a","SWP391", 5, "2022-12-30T20:00:00 00:00Z","false", null, SearchRequest.SearchOrder.DATE_ASC,10,null);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
        er = searchController.searchPost(null,null, null, null,"true", null, null,10,1);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
    }

    @Test
    void testIsTestParam() {
        ResponseEntity<String> er = searchController.searchPost("a","SWP391", 5, "2022-12-30T20:00:00 00:00Z","", null, SearchRequest.SearchOrder.TITLE_DESC,10,1);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
    }

    @Test
    void testIsTestAndOrderParam() {
        ResponseEntity<String> er = searchController.searchPost("a","SWP391", 5, "2022-12-30T20:00:00 00:00Z","true", null, SearchRequest.SearchOrder.DATE_ASC,10,null);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
        er = searchController.searchPost(null,null, null, null,"false", null, null,10,1);
        assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
    }
}
