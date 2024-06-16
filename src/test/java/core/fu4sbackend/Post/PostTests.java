package core.fu4sbackend.Post;

import core.fu4sbackend.controller.PostController;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostTests {
    @Autowired PostController controller;

    @Test
    public void testGetAll() {
        String response = controller.showAllPosts(null,null,null,null,null,null, null,1,null)
                .getBody();
        int num = Integer.parseInt(response.substring("{\"total\":".length()+1,response.indexOf(',')));
        assert (num>0&&response.indexOf("id")==response.lastIndexOf("id"));
    }

    @Test
    public void testUserPost() {
        List<PostDto> list= controller.showAllPostsByUsername("user02",1,100).getBody();
        assert (list.size()==controller.getNumPosts("user02").getBody());
    }
}
