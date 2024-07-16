package core.fu4sbackend.controller;

import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.service.PostService;
import core.fu4sbackend.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;
    private final PostService postService;


    public SubjectController(SubjectService subjectService, PostService postService) {
        this.subjectService = subjectService;
        this.postService = postService;
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

    @GetMapping("/{code}")
    public ResponseEntity<SubjectDto> getSubjectByCode(@PathVariable String code) {
        Optional<SubjectDto> subjectDto = subjectService.getSubjectByCode(code);
        return subjectDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/semester/{semester}")
    public List<SubjectDto> getSubjectsBySemester(@PathVariable int semester) {
        return subjectService.getSubjectsBySemester(semester);
    }
    @GetMapping("/getAllSubjectCodes")
    public ResponseEntity<List<String>> getAllSubjectCodes() {
        List<String> subjectCodes = subjectService.getAllSubjectCodes();
        return ResponseEntity.ok(subjectCodes);
    }
}
