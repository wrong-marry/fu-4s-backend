package core.fu4sbackend.service;

import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findByUsername(username).orElse(null);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
