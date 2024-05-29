package core.fu4sbackend.service;

import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.Notification;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.SubjectRepository;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDto> getAllSubjectDtos() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        subjectDtos = subjects
                .stream()
                .map(Subject -> {
                    return modelMapper.map(Subject, SubjectDto.class);
                })
                .collect(Collectors.toList());

        return subjectDtos;
    }

}
