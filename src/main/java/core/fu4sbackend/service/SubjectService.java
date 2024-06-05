package core.fu4sbackend.service;

import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDto> getAllSubjectDtos(Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        List<Subject> subjects = subjectRepository.findAll(paging).toList();
        ModelMapper modelMapper = new ModelMapper();

        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    public Integer getNumberOfSubjects() {
        return subjectRepository.findAll().size();
    }

    public void deleteSubject(String subjectCode) {
        Subject subject = (Subject) subjectRepository.findByCode(subjectCode);
        subjectRepository.delete(subject);
    }


    public List<SubjectDto> getAll() {
        List<Subject> subjects = subjectRepository.findAllByOrderBySemesterAsc();

        ModelMapper modelMapper = new ModelMapper();
        return subjects.stream()
                .map((subject -> modelMapper.map(subject, SubjectDto.class)))
                .toList();
    }

    public void disableSubject(String subjectCode) {
        Subject subject = subjectRepository.findByCode(subjectCode);
        subject.setActive(false);
        subjectRepository.save(subject);
    }
}

