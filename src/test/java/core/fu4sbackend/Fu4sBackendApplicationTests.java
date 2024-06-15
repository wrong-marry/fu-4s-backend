package core.fu4sbackend;

import core.fu4sbackend.controller.AuthController;
import core.fu4sbackend.controller.SearchController;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.security.AuthenticationService;
import core.fu4sbackend.security.LoginDTO;
import core.fu4sbackend.security.RegisterDTO;
import core.fu4sbackend.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class Fu4sBackendApplicationTests {
	@Autowired
	AuthController authController;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	UserService userService;
    @Autowired
    private SearchController searchController;

	@Test
	void contextLoads() {
	}

	@Test
	void testAuthentication() {
		RegisterDTO registerDTO = new RegisterDTO("user11","pasword123","First","Last","email");
		JSONObject registerResponse = authenticationService.signup(registerDTO);
		UserDto u = userService. getByUsername(registerResponse.getString("username"));
		assert (u.getFirstName().equals(registerDTO.getFirstName())&&u.getEmail().equals(registerDTO.getEmail()));

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsername(registerDTO.getUsername());
		loginDTO.setPassword(registerDTO.getPassword()+"False");
		assertThrows(org.springframework.security.authentication.BadCredentialsException.class,()->authController.authenticate(loginDTO));
	}

	@Test
	void testSearch() {
		ResponseEntity<String> er = searchController.searchPost("a","SWP391", 5, "2022-12-30T20:00:00 00:00Z","false", null, SearchRequest.SearchOrder.DATE_ASC,10,null);
		assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
		er = searchController.searchPost(null,null, null, null,null, null, null,10,1);
		assert (er.getStatusCode()== HttpStatus.OK && er.getBody()!=null && er.getBody().length()>21);
	}

	@Test
	void testComment() {

	}
}
