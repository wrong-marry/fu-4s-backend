package core.fu4sbackend.controller;

import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // ----
@RequestMapping("/api/v1/questionSet") //---
public class QuestionSetController {

    private final QuestionSetService questionSetService;
    @Autowired
    public QuestionSetController(QuestionSetService questionSetService){
        this.questionSetService = questionSetService ;
    }

    @GetMapping("/getAll")
    public List<QuestionSetDto> getAllQuestionSets(){
        List<QuestionSetDto> questionSets = questionSetService.getAllQuestionSets();
        return questionSets;
    }

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<QuestionSetDto>> getAllQuestionSetsByUsername(
            @RequestParam String username,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ) {
        --pageNum;
        return ResponseEntity.ok(questionSetService.getQuestionSetsByUsername(username, pageNum, pageSize));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeQuestionSet(@RequestParam Integer id,
                                                        @RequestParam String username) throws Exception {
        questionSetService.removeQuestionSet(id, username);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("")
    public ResponseEntity<QuestionSetDto> getQuestionSetById(@RequestParam(value = "id") String id) {
        QuestionSetDto questionSetDto = null;
        try {
            questionSetDto = questionSetService.getQuestionSetById(Integer.valueOf(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(questionSetDto);
    }

    @GetMapping("/getNum")
    public ResponseEntity<Integer> getNumQuestionSets(@RequestParam String username){
        return ResponseEntity.ok(questionSetService.getNumberOfQuestionSets(username));
    }

    @PostMapping("/addNew")
    public ResponseEntity<QuestionSetDto> addNewQuestionSet(
            @RequestParam String title,
            @RequestParam String subjectCode,
            @RequestBody List<QuestionDto> questionDtoList,
            @RequestParam String username
    ) {
        return ResponseEntity.ok(questionSetService.addNewQuestionSet(title, subjectCode, questionDtoList, username));
    }

    @GetMapping("/getById")
    public ResponseEntity<QuestionSetDto> getById(@RequestParam Integer id) {
        return ResponseEntity.ok(questionSetService.getById(id));
    }

    @PutMapping("/increase-attempts")
    public String increaseAttempts(@RequestParam Integer id) {
        return questionSetService.increaseAttempts(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<QuestionSetDto> editQuestionSet(
            @RequestParam Integer id,
            @RequestParam String title,
            @RequestParam String subjectCode,
            @RequestBody List<QuestionDto> questionDtoList,
            @RequestParam String username
    ) throws Exception {
        return ResponseEntity.ok(questionSetService.editQuestionSet(id, title, subjectCode, questionDtoList, username));
    }
}
