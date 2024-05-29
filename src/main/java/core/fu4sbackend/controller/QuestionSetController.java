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
@CrossOrigin
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

    @PutMapping("/editQuestionSet")
    public ResponseEntity<QuestionSetDto> editQuestionSet(@RequestBody QuestionSetDto questionSetDto,
                                                   @RequestParam String username) throws Exception {
        questionSetService.editQuestionSet(questionSetDto, username);
        return ResponseEntity.ok(questionSetDto);
    }

    @DeleteMapping("/removeQuestionSet")
    public ResponseEntity<String> removeQuestionSet(@RequestParam Integer id,
                                                        @RequestParam String username) throws Exception {
        questionSetService.removeQuestionSet(id, username);
        return ResponseEntity.ok("ok");
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
}
