package core.fu4sbackend.Comment;

import core.fu4sbackend.constant.CommentStatus;
import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.controller.CommentController;
import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CommentTests {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentController commentController;


    @Test
    void testComment() {
        List<CommentDto> comments = new ArrayList<>();
        final List<CommentDto> sample = commentService.findByPostId(15,0, false, false);
        comments.addAll( sample);
        comments.addAll( commentService.findByPostId(15, PaginationConstant.COMMENT_LOAD_SIZE-1, false, false));
        assert (comments.get(PaginationConstant.COMMENT_LOAD_SIZE).getId()==comments.get(PaginationConstant.COMMENT_LOAD_SIZE-1).getId());

        assert (commentService.findByPostId(15, 0, true, false).stream()
                .noneMatch(c -> c.getStatus() == CommentStatus.HIDDEN) || sample.stream()
                .allMatch(c -> c.getStatus() == CommentStatus.ACTIVE)
        );
    }
}
