package core.fu4sbackend.controller;

import core.fu4sbackend.dto.TestResultDto;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-result")
public class TestResultController {
    private final TestResultService testResultService;
    @Autowired
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }
    @GetMapping
    public List<TestResultDto> getTestResultsByUser(@RequestParam String username,@RequestParam boolean isPersonalized) {
        return testResultService.getTestResultsByUsernameAndPersonalized(isPersonalized, username);
    }
    @GetMapping("get-last-score")
    public float getLastScoreByUser(@RequestParam String username,@RequestParam int questionSetId) {
        return testResultService.getLastScoreByUserAndQuestionSetId(username,questionSetId);
    }
    @PostMapping("save")
    public TestResultDto saveTestResult(@RequestParam float score,@RequestParam String username,@RequestParam int questionSetId,@RequestParam boolean isPersonalized) {
        return testResultService.saveTestResult(score,username,questionSetId,isPersonalized);
    }
    @GetMapping("get-all")
    public List<TestResultDto> getAllTestResultsByUser(@RequestParam String username) {
        return testResultService.getTestResultsByUser(username);
    }
}
