package core.fu4sbackend.Post;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.controller.PostController;
import core.fu4sbackend.controller.StaffController;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostTests {
    @Autowired PostController controller;

    @Autowired
    StaffController staffController;

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

    @Test
    public void testRecentPostAndPaging() {
        List<PostDto> list= controller.getRecentPost(null).getBody();
        List<PostDto> list2 = controller.getRecentPost(1).getBody();
        assert (list.get(1).getId()==list2.getFirst().getId());
    }

    @Test
    void checkPostAndStatus() {
        assert (staffController.getNumPostsEachStatus(PostStatus.ACTIVE).getBody()>=0&&
                staffController.getNumPostsEachStatus(PostStatus.HIDDEN).getBody()>=0&&
                staffController.getNumPostsEachStatus(PostStatus.PENDING_APPROVE).getBody()>=0);
    }

    @Test
    void getPostByHiddenStatus() {
        assert (staffController.showAllPostsByPostStatus(PostStatus.HIDDEN,1,99).getBody()
                .size()==staffController.getNumPostsEachStatus(PostStatus.HIDDEN).getBody());
    }
    @Test
    void getPostByPendingStatus() {
        assert (staffController.showAllPostsByPostStatus(PostStatus.PENDING_APPROVE,1,99).getBody()
                .size()==staffController.getNumPostsEachStatus(PostStatus.PENDING_APPROVE).getBody());
    }
    @Test
    void getPostByActiveStatus() {
        assert (staffController.showAllPostsByPostStatus(PostStatus.ACTIVE,1,99).getBody()
                .size()==staffController.getNumPostsEachStatus(PostStatus.ACTIVE).getBody());
    }
}
