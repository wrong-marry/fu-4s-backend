package core.fu4sbackend.Comment;

import core.fu4sbackend.constant.CommentStatus;
import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.controller.CommentController;
import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class CommentTests {
    String content = Math.random()+"";
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentController commentController;


    @Test
    void testCommentPaging() {
        List<CommentDto> comments = new ArrayList<>();
        final List<CommentDto> sample = commentController.getCommentsInAPost(15,"0", null, null).getBody();
        comments.addAll( sample);
        comments.addAll( commentService.findByPostId(15, PaginationConstant.COMMENT_LOAD_SIZE-1, false, false));
        assert (comments.get(PaginationConstant.COMMENT_LOAD_SIZE).getId()==comments.get(PaginationConstant.COMMENT_LOAD_SIZE-1).getId());
    }

    @Test
    void testSaveInvalidComment() {
        Date date = new Date();
        CommentDto cd = new CommentDto(-1,date,"test: "+content, CommentStatus.ACTIVE, "Some name", "user03", 0);
        assert(commentController.uploadComment(1,cd).getStatusCode()== HttpStatus.CONFLICT);
    }

    @Test
    void testCommentStatus() {
        List<CommentDto> comments = new ArrayList<>();
        final List<CommentDto> sample = commentService.findByPostId(15,0, false, false);
        comments.addAll( sample);
        comments.addAll( commentService.findByPostId(15, PaginationConstant.COMMENT_LOAD_SIZE-1, false, false));
        assert (commentService.findByPostId(15, 0, true, false).stream()
                .noneMatch(c -> c.getStatus() == CommentStatus.HIDDEN) || sample.stream()
                .allMatch(c -> c.getStatus() == CommentStatus.ACTIVE)
        );
    }

    @Test
    void testUpdateComment() {
        final List<CommentDto> sample = commentService.findByPostId(15,0, true, true);
        CommentDto c = sample.getFirst();
        c.setContent(sample.getFirst().getContent()+content);
        commentController.updateComment(sample.getFirst().getId(),c);
        commentController.updateCommentStatus(sample.getFirst().getId());

        CommentDto newComment = commentController.getCommentsInAPost(15,"0", "true", "true").getBody().getFirst();
        System.out.println("OLD: " +sample.getFirst().getContent());
        assert (Objects.equals(newComment.getContent(), c.getContent()) &&newComment.getStatus()!=c.getStatus());
    }

    @Test
    void testUploadDeleteChild() {
        Date date = new Date();
        CommentDto cd = new CommentDto(-1,date,"test: "+content, CommentStatus.ACTIVE, "user03", "user03", 0);
        int newId = commentService.save(cd,1);
        CommentDto newChild1 = new CommentDto(-1, date, content+"1", CommentStatus.ACTIVE, "user04", "user03", 0);
        CommentDto newChild2 = new CommentDto(-1, date, content+"2", CommentStatus.ACTIVE, "user04", "user03", 0);
        CommentDto newChild3 = new CommentDto(-1, date, content+"3", CommentStatus.ACTIVE, "user04", "user03", 0);

        int childId = commentService.saveChild(newChild1,newId);
        commentService.saveChild(newChild2,newId);
        commentService.saveChild(newChild3,newId);
        List<CommentDto> children = commentService.getAllChildren(newId,"0");
        if ( children.size()==3) {
            commentController.deleteComment(newId);
            Assertions.assertThrows(Exception.class,()->commentService.update(childId,"Test"));
        } else assert (false);
    }
}
