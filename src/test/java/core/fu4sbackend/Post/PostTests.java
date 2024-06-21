package core.fu4sbackend.Post;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.controller.PostController;
import core.fu4sbackend.controller.StaffController;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class PostTests {
    @Autowired PostController controller;

    @ParameterizedTest
    @CsvSource({})
    public void testGetAll(String title, String subjectCode, Integer semester, Date postTime, Boolean isTest, String username, SearchRequest.SearchOrder order, Integer pageSize, Integer page) {
        String response = controller.showAllPosts(title,subjectCode,semester,postTime,isTest,username, order,pageSize,page)
                .getBody();
        int num = Integer.parseInt(response.substring("{\"total\":".length()+1,response.indexOf(',')));
        assert (num>0&&response.indexOf("id")==response.lastIndexOf("id"));
    }

    @ParameterizedTest
    @CsvSource({})
    public void testGetRecentPosts(Integer offset) {
        List<PostDto> response = controller.getRecentPost(offset)
                .getBody();
        assert (true);
    }

    @ParameterizedTest
    @CsvSource({})
    public void testGetUserPosts(String username, Integer pageNum, Integer pageSize) {
        List<PostDto> response = controller.showAllPostsByUsername(username,pageNum,pageSize).getBody();
        assert (true);
    }

    @ParameterizedTest
    @CsvSource({})
    public void testCountUserPosts(String username) {
        Integer response = controller.getNumPosts(username).getBody();
        assert (true);
    }
}
