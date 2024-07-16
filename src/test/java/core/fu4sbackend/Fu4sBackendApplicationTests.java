package core.fu4sbackend;

import core.fu4sbackend.controller.CommentController;
import core.fu4sbackend.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Fu4sBackendApplicationTests {
	public enum ExpectedTestResult {
		EXCEPTION,
		NORMAL,
		ABNORMAL_RESPONSE
	}

}
