package core.fu4sbackend.service;

import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDto> getAll() {
        List<Subject> subjects = subjectRepository.findAllByOrderBySemesterAsc();

        ModelMapper modelMapper = new ModelMapper();
        return subjects.stream()
                .map((subject -> modelMapper.map(subject, SubjectDto.class)))
                .toList();
    }
}
