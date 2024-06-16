package core.fu4sbackend.Subject;

import core.fu4sbackend.controller.SubjectController;
import core.fu4sbackend.dto.SubjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SubjectTests {
    @Autowired
    SubjectController subjectController;

    @Test
    public void getSubject() {
        int num = subjectController.getNumberOfSubjects().getBody();
        List<SubjectDto> list = subjectController.getAllSubjects(1,num+1);
        assert (!list.isEmpty() && list.size()==num);
    }
}
