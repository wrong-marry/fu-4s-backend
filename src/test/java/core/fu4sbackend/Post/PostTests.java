package core.fu4sbackend.Post;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import core.fu4sbackend.Fu4sBackendApplicationTests;
import core.fu4sbackend.controller.PostController;
import core.fu4sbackend.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PostTests {
//    @Autowired PostController controller;
//
//    @ParameterizedTest
//    @CsvSource({"a,1,swp,2023-03-24T23:00:00 00:00Z,0,NORMAL",
//            "'',9,'',,1,EXCEPTION",
//            ",,,2023-03-24T23:00:00 00:00Z,0,NORMAL",
//            "a,1,swp,2023-03-24T23:00:00 00:00Z,1,NORMAL",
//            "a,10,swp,2023-03-24T23:00:00 00:00Z,0,EXCEPTION",
//            "a,0,swp,2023-03-24T23:00:00 00:00Z,0,EXCEPTION",
//            "a,1,swp,2023-02-29T23:00:00 00:00Z,0,EXCEPTION",
//            "a,1,swp,2023-03-24T23:00:00 00:00Z,-1,EXCEPTION",
//            "a,1,swp,'',0,EXCEPTION",
//    })
//    public void testGetAll(String title, Integer semester,String subjectCode, String postTime, Integer page, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws ParseException {
//
//        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
//        {
//            Date time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss SS:SS'Z'").parse(postTime);
//            assert controller.showAllPosts(title, subjectCode, semester, time, null, null, null, 3, page)
//                    .getStatusCode()== HttpStatus.OK;
//        }
//        else {
//
//            Assertions.assertThrows(Exception.class,()->{
//                Date time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss SS:SS'Z'").parse(postTime);
//                String response = controller.showAllPosts(title, subjectCode, semester, time, null, null, null, 3, page)
//                        .getBody();
//                int num = Integer.parseInt(response.substring("{\"total\":".length() + 1, response.indexOf(',')));
//                assert (num > 0 && response.indexOf("id") == response.lastIndexOf("id"));
//            });
//        }
//    }
//
//    @ParameterizedTest
//    @CsvSource({"0,NORMAL","-1,EXCEPTION"})
//    public void testGetRecentPosts(Integer offset, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
//        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
//        {
//            List<PostDto> response = controller.getRecentPost(offset)
//                    .getBody();
//            assert (true);
//        }
//        else Assertions.assertThrows(Exception.class,()->{
//            List<PostDto> response = controller.getRecentPost(offset)
//                    .getBody();
//            assert (true);
//        });
//    }
//
//    @ParameterizedTest
//    @CsvSource({"user01,NORMAL","user-1,EXCEPTION"})
//    public void testGetUserPosts(String username, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
//        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
//        {
//            List<PostDto> response = controller.showAllPostsByUsername(username, 1, 3).getBody();
//            assert (true);
//        }else Assertions.assertThrows(Exception.class,()->{
//            List<PostDto> response = controller.showAllPostsByUsername(username, 1, 3).getBody();
//            assert (true);
//        });
//    }
//
//    @ParameterizedTest
//    @CsvSource({"user01,NORMAL","user-1,EXCEPTION"})
//    public void testCountUserPosts(String username, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) {
//        if (expectedTestResult== Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
//        {
//            Integer response = controller.getNumPosts(username).getBody();
//            assert (true);
//        }else Assertions.assertThrows(Exception.class,()->{
//            List<PostDto> response = controller.showAllPostsByUsername(username, 1, 3).getBody();
//            assert (true);
//        });
//    }
}
