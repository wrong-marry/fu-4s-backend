package core.fu4sbackend;

import core.fu4sbackend.controller.CommentController;
import core.fu4sbackend.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Fu4sBackendApplicationTests {
	@Autowired
	CommentController commentController;
    @Autowired
    private CommentService commentService;

	@Test
	void contextLoads() {
	}

}
