package core.fu4sbackend.Comment;

import core.fu4sbackend.Fu4sBackendApplicationTests;
import core.fu4sbackend.constant.CommentStatus;
import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.controller.CommentController;
import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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
    void testCommentPagingAndVisibility() throws Exception {
        CommentDto commentDto = new CommentDto(-1,new Date(),"test",CommentStatus.ACTIVE,"user03","user03",0);
        for (int i=0; i<10; i++){
            commentController.uploadComment(15,commentDto);
        }
        List<CommentDto> comments = new ArrayList<>();
        final List<CommentDto> sample = commentController.getCommentsInAPost(15,"0", null, null).getBody();
        comments.addAll( sample);
        comments.addAll( commentController.getCommentsInAPost(15,PaginationConstant.COMMENT_LOAD_SIZE-1+"",null, null).getBody());
        int lastItemId = comments.getLast().getId();
        commentController.updateCommentStatus(lastItemId);
        assert (comments.get(PaginationConstant.COMMENT_LOAD_SIZE).getId()==comments.get(PaginationConstant.COMMENT_LOAD_SIZE-1).getId()
        && commentController.getCommentsInAPost(15,PaginationConstant.COMMENT_LOAD_SIZE-1+"", null, null).getBody().getLast().getId()!=lastItemId);
    }

    @Test
    void testSaveInvalidComment() throws Exception {
        Date date = new Date();
        CommentDto cd = new CommentDto(-1,date,"test: "+content, CommentStatus.ACTIVE, "Some name", "user03", 0);
        assert(commentController.uploadComment(1,cd).getStatusCode()== HttpStatus.CONFLICT);
        CommentDto cd2 = new CommentDto(-1,date,"test: "+content, CommentStatus.ACTIVE, "user01", "user03", 0);
        assert(commentController.uploadComment(-1,cd).getStatusCode()== HttpStatus.CONFLICT);
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
    void testUploadDeleteChild() throws Exception {
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

    @Test
    void testUploadInvalidContent() {
        CommentDto newComment = new CommentDto(-1, new Date(), null, CommentStatus.ACTIVE, "user04", "user03", 0);
        Assertions.assertThrows(Exception.class,()->commentController.uploadComment(1,newComment));
        CommentDto newComment2 = new CommentDto(-1, new Date(), "", CommentStatus.ACTIVE, "user04", "user03", 0);
        Assertions.assertThrows(Exception.class,()->commentController.uploadComment(1,newComment2));
    }

    @Test
    void testGetUpdateChildren() throws Exception {
        CommentDto newComment = new CommentDto(-1, new Date(), "test", CommentStatus.ACTIVE, "user04", "user03", 0);
        int newid = commentService.save(newComment,1);
        int child = commentService.saveChild(newComment,newid);
        assert (commentController.updateComment(child+1,newComment).getStatusCode()==HttpStatus.CONFLICT);
        assert (commentController.getChildrenComment(newid,"0").getBody().getFirst().getId()==child);
    }

    @Test
    void updateInvalidCommentStatus() {
        assert (commentController.updateCommentStatus(-1).getStatusCode()==HttpStatus.CONFLICT);
    }

    @Test
    void uploadChildComment() {
        CommentDto child1= new CommentDto(-1, new Date(), "test", CommentStatus.ACTIVE, "user04", "user03", 0);
        assert (commentController.uploadChildComment(1,child1).getStatusCode()==HttpStatus.OK);
    }

    @Test
    void uploadInvalidChildComment() {
        CommentDto child1= new CommentDto(-1, new Date(), "test", CommentStatus.ACTIVE, "", "", 0);
        assert (commentController.uploadChildComment(1,child1).getStatusCode()==HttpStatus.CONFLICT);

        CommentDto child2= new CommentDto(-1, new Date(), "test", CommentStatus.ACTIVE, null, null, 0);
        assert (commentController.uploadChildComment(1,child1).getStatusCode()==HttpStatus.CONFLICT);
    }

    @ParameterizedTest
    @CsvSource({"22,,,,NORMAL",
            "'',,,,EXCEPTION",
            "22,1,TRUE,FALSE,NORMAL",
            "22,,FALSE,TRUE,NORMAL",
            "22,'',FALSE,TRUE,EXCEPTION",
            "22,,'','',NORMAL",})
    void testPostComments(int id, String offset, String isStaff, String sorted, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            List<CommentDto> list = commentController.getCommentsInAPost(id, offset, isStaff, sorted).getBody();
            assert (list.stream().allMatch(c -> c.getId() >= list.getFirst().getId()));
        }
    }

    @ParameterizedTest
    @CsvSource({"content,user06,NORMAL",
            ",user06,EXCEPTION",
            "'',user06,EXCEPTION",
            "content,user0,EXCEPTION",
            "content,,EXCEPTION",
            "content,'',EXCEPTION",})
    void testUploadComment(String newContent, String username, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws Exception {
        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            Date date = new Date();
            CommentDto cd = new CommentDto(-1, date, newContent, CommentStatus.ACTIVE, username, username, 0);
            assert (commentController.uploadComment(1, cd).getStatusCode() == HttpStatus.CONFLICT);
        }
    }

    @ParameterizedTest
    @CsvSource({"1,content,user06,NORMAL",
            "-1,content,user06,EXCEPTION",
            "1,,user06,EXCEPTION",
            "1,'',user06,EXCEPTION",
            "1,content,null,EXCEPTION",
    })
    void testUploadChildComment(int id, String newContent, String username, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws Exception {
        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            Date date = new Date();
            CommentDto cd = new CommentDto(-1, date, newContent, CommentStatus.ACTIVE, username, username, 0);
            assert (commentController.uploadChildComment(id, cd).getStatusCode() == HttpStatus.CONFLICT);
        }
    }

    @ParameterizedTest
    @CsvSource({"22,content,NORMAL",
            "22,,EXCEPTION",
            "22,'',EXCEPTION",
            "-22,content,EXCEPTION",
            ",content,EXCEPTION"
    })
    void testUpdateComment(int id, String newContent, Fu4sBackendApplicationTests.ExpectedTestResult expected) throws Exception {
        if (expected== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        {
            CommentDto c = new CommentDto();
            c.setContent(newContent);
            assert (commentController.updateComment(id, c).getStatusCode() == HttpStatus.OK);
        }
    }

    @ParameterizedTest
    @CsvSource({"22,NORMAL",
            "-22,EXCEPTION",})
    void testDeleteComment(int id, Fu4sBackendApplicationTests.ExpectedTestResult expected) throws Exception {
        if (expected== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        assert (commentController.deleteComment(id).getStatusCode()==HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({"22,NORMAL",
            "-22,EXCEPTION",})
    void testHideUnhideComment(int id, Fu4sBackendApplicationTests.ExpectedTestResult expected) throws Exception {
        if (expected== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        assert (commentController.updateCommentStatus(id).getStatusCode()==HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({"11,NORMAL",
            "-22,EXCEPTION",})
    void testGetChildrenComment(int id, String offset, Fu4sBackendApplicationTests.ExpectedTestResult expected) throws Exception {
        if (expected== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        assert (commentController.getChildrenComment(id, offset).getStatusCode()==HttpStatus.OK);
        else {
            Assertions.assertThrows(Exception.class,()->{
                commentController.getChildrenComment(id, offset);
            });
        }
    }
}
