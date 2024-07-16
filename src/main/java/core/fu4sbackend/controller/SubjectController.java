package core.fu4sbackend.controller;

import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.service.NotificationService;
import core.fu4sbackend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/getAllSubject")
    public List<SubjectDto> getAllSubjects(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize){
        --pageNum;
        List<SubjectDto> subjectDtoList = subjectService.getAllSubjectDtos(pageNum, pageSize);
        return subjectDtoList;
    }

    @GetMapping("/getNumSubject")
    public ResponseEntity<Integer> getNumberOfSubjects(){
        return ResponseEntity.ok(subjectService.getNumberOfSubjects());
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<SubjectDto>> getAll() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @GetMapping("/getAllSubjectCodes")
    public ResponseEntity<List<String>> getAllSubjectCodes() {
        List<String> subjectCodes = subjectService.getAllSubjectCodes();
        return ResponseEntity.ok(subjectCodes);
    }
}
