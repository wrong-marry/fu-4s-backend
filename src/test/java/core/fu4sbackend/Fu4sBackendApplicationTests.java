package core.fu4sbackend;

import core.fu4sbackend.constant.CommentStatus;
import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.controller.AuthController;
import core.fu4sbackend.controller.CommentController;
import core.fu4sbackend.controller.SearchController;
import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.security.AuthenticationService;
import core.fu4sbackend.security.LoginDTO;
import core.fu4sbackend.security.RegisterDTO;
import core.fu4sbackend.service.CommentService;
import core.fu4sbackend.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class Fu4sBackendApplicationTests {
	@Test
	void contextLoads() {
	}


}
