
package core.fu4sbackend.controller;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.dto.*;
import core.fu4sbackend.service.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@Secured("ADMIN")
public class AdminController {
    private final UserService userSer;
    private final SubjectService subjectService;
    private final CommentService commentService ;
    private final TestResultService testResultService;

    @Autowired
    public AdminController(UserService userSer, SubjectService subjectService, CommentService commentService, TestResultService testResultService) {
        this.userSer = userSer;
        this.subjectService = subjectService;
        this.commentService = commentService;
        this.testResultService = testResultService;
    }

    @GetMapping("/getAllUser")
    public List<UserPostCountDto> getAllUsers(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize){
        --pageNum;
        List<UserPostCountDto> userDtoList = userSer.getAllUsers(pageNum, pageSize);
        return userDtoList;
    }
//    @GetMapping("/getAllUserNoPaging")
//    public List<UserDto> getAllUsersNoPaging(){
//        List<UserDto> userDtoList = userSer.getAllUsers();
//        return userDtoList;
//    }


    @GetMapping("/getNumUser")
    public ResponseEntity<Integer> getNumberOfUsers(){
        return ResponseEntity.ok(userSer.getNumberOfUsers());
    }

    @GetMapping("/getAllByUserRole")
    public List<UserPostCountDto> getAllByUserRole(@RequestParam UserRole userrole,
                                                       @RequestParam Integer pageNum,
                                                       @RequestParam Integer pageSize){
        --pageNum;
        List<UserPostCountDto> userDtoList = userSer.getAllByUserRole(userrole,pageNum, pageSize);
        return userDtoList;
    }
    @GetMapping("/getNumEachRole")
    public ResponseEntity<Integer> getNumUsesEachRole(@RequestParam UserRole userrole){
        return ResponseEntity.ok(userSer.getNumberOfUserByRole(userrole));
    }


    @PutMapping("disableSubject")
    public ResponseEntity<String> deactiveSubject(@RequestParam("subjectCode") String subjectCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            subjectService.deactiveSubject(subjectCode);
            jsonObject.put("message", "Subject deactivated successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/activeSubject")
    public ResponseEntity<String> activeSubject(@RequestParam("subjectCode") String subjectCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            subjectService.activeSubject(subjectCode);
            jsonObject.put("message", "Active successfully");
          return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/banUser")
    public ResponseEntity<String> banUser(@RequestParam("username") String username ) {
        JSONObject jsonObject = new JSONObject();
        try {
            userSer.banUser(username);
            jsonObject.put("message", "Banned user successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getNumSubject")
    public ResponseEntity<Integer> getNumberOfSubjects(){
        return ResponseEntity.ok(subjectService.getNumberOfSubjects());
    }

    @GetMapping("/getNumSubjectsByType")
    public ResponseEntity<Integer> getNumberOfSubjectsByType(@RequestParam boolean isActive) {
        return ResponseEntity.ok(subjectService.getNumberOfSubjectsByType(isActive));
    }

    @PutMapping("/updateSubject")
    public ResponseEntity<String> updateSubject(@RequestBody SubjectDto subjectDto) {
        JSONObject jsonObject = new JSONObject();
        String message = switch (subjectService.update(subjectDto)) {
            case -1:
                yield "Invalid subject code";
            case 0:
                yield "Successfully updated subject";
            default:
                yield "Something went wrong";
        };
        jsonObject.put("message", message);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }


    @PostMapping("/createSubject")
    public ResponseEntity<String> createSubject(@RequestBody SubjectDto subjectDto) {
        JSONObject jsonObject = new JSONObject();
        try {
            subjectService.createSubject(subjectDto);
            jsonObject.put("message", "Subject created successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
          } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/activateUser")
    public ResponseEntity<String> activeUser(@RequestParam("username") String username) {
        JSONObject jsonObject = new JSONObject();
        try {
            userSer.activateUser(username);
            jsonObject.put("message", "Active successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);

        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/compareNumberAccounts")
    public ResponseEntity<Integer> compareAccountsThisMonthLastMonth() {
        JSONObject jsonObject = new JSONObject();
        try {
            int numOfAccountsNow = userSer.getNumberOfUsers();
            int numOfAccountsThisMonth = userSer.getNumberOfUsersThisMonth();

            double percentChange = userSer.calculatePercentageChange(numOfAccountsNow-numOfAccountsThisMonth, numOfAccountsThisMonth);
            int percentChangeInt = (int) percentChange;

            return new ResponseEntity<>(percentChangeInt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getNumComments")
    public ResponseEntity<Integer> getNumberOfComments(){
        return ResponseEntity.ok(commentService.getNumberOfComments());
    }
    @GetMapping("/getNumTestResults")
    public ResponseEntity<Integer> getNumberOfTestResults(){
        return ResponseEntity.ok(testResultService.getNumberOfTestResults());
    }
    @GetMapping("/percentTestResultsNew")
    public ResponseEntity<Integer> PercentTestResultsNew() {
        try {
            int numOfTestResultsNow = testResultService.getNumberOfTestResults();
            int numOfTestResultsThisMonth = testResultService.getNumberOfTestResultsThisMonth();

            double percentChange = testResultService.calculatePercentageChangeTestResult(numOfTestResultsNow-numOfTestResultsThisMonth, numOfTestResultsThisMonth);
            int percentChangeInt = (int) percentChange;

            return new ResponseEntity<>(percentChangeInt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/percentCommentsNew")
    public ResponseEntity<Integer> PercentCommentsNew() {
        try {
            int numOfCommentsNow = commentService.getNumberOfComments();
            int numOfCommentsThisMonth = commentService.getNumberOfCommentsThisMonth();

            double percentChange = commentService.calculatePercentageChangeComment(numOfCommentsNow-numOfCommentsThisMonth, numOfCommentsThisMonth);
            int percentChangeInt = (int) percentChange;

            return new ResponseEntity<>(percentChangeInt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/promoteUser")
    public ResponseEntity<String> promoteUser(@RequestParam("username") String username) {
        JSONObject jsonObject = new JSONObject();
        try {
            userSer.promoteUser(username);
            jsonObject.put("message", "Active successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);

        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/demoteUser")
    public ResponseEntity<String> demoteUser(@RequestParam("username") String username) {
        JSONObject jsonObject = new JSONObject();
        try {
            userSer.demoteUser(username);
            jsonObject.put("message", "Active successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);

        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createDirectNoti")
    public ResponseEntity<String> createDirectNoti(@RequestBody DirectNotificationDto notiDto) {
        JSONObject jsonObject = new JSONObject();
        try {
            NotificationService.createDirectNoti(notiDto);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}





