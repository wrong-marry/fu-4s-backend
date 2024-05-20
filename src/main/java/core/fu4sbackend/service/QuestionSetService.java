package core.fu4sbackend.service;

import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.QuestionSetRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionSetService {
    @Autowired
    private QuestionSetRepository questionSetRepository;
    public QuestionSet getById(int id) {
        return questionSetRepository.getById(id);
    }
    public void save(QuestionSet questionSet) {
        questionSetRepository.save(questionSet);
    }
}
