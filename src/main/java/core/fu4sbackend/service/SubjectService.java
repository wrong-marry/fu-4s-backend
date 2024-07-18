package core.fu4sbackend.service;

import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public void deactiveSubject(String subjectCode) {
        Subject subject = subjectRepository.findByCode(subjectCode);
        subject.setActive(false);
        subjectRepository.save(subject);
    }

    public void activeSubject(String subjectCode) {
        Subject subject = subjectRepository.findByCode(subjectCode);
        subject.setActive(true);
        subjectRepository.save(subject);
    }
    public Integer getNumberOfSubjectsByType(boolean isActive) {
        return subjectRepository.countByIsActive(isActive);
    }


    public int update(SubjectDto subjectDto) {
        Optional<Subject> optionalSubject = Optional.ofNullable(subjectRepository.findByCode(subjectDto.getCode()));
        if (optionalSubject.isEmpty()) {
            return -1; // Invalid subject id
        }
        Subject subject = optionalSubject.get();
        subject.setName(subjectDto.getName());
        subject.setCode(subjectDto.getCode());
        subjectRepository.save(subject);
        return 0; // Successfully updated subject
    }


    @Transactional
    public void createSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setCode(subjectDto.getCode());
        subject.setName(subjectDto.getName());
        subject.setSemester(subjectDto.getSemester());
        subject.setActive(true); // assuming new subjects are active by default
        subjectRepository.save(subject);
    }

    public Optional<SubjectDto> getSubjectByCode(String code) {
        ModelMapper modelMapper = new ModelMapper();
        return Optional.ofNullable(subjectRepository.findByCode(code))
                .map(subject -> modelMapper.map(subject, SubjectDto.class));
    }


    public List<SubjectDto> getSubjectsBySemester(int semester) {
        List<Subject> subjects = subjectRepository.findBySemester(semester);
        ModelMapper modelMapper = new ModelMapper();
        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }
    public List<String> getAllSubjectCodes() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(Subject::getCode)
                .collect(Collectors.toList());
    }
}

