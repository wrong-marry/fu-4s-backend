package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findByUsername(username).orElse(null);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        userDtos = userList
                .stream()
                .map(user  -> {
                    UserDto userDto =  modelMapper.map(user, UserDto.class);
                    return userDto ;
                })
                .collect(Collectors.toList());
        return userDtos;
    }

    public UserDto editEmailFirstNameLastName(UserDto newUser,String userName){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRepository.findByUsername(userName)
                .map(user -> {user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return userRepository.save(user);})
                .orElseThrow(()->new UsernameNotFoundException(userName)),UserDto.class);
    }
    public boolean compareOldAndNewPassword(String username,String confirmPassword){
        return passwordEncoder.matches(confirmPassword
                        ,userRepository
                        .findByUsername(username)
                        .orElseThrow(()->new UsernameNotFoundException(username))
                        .getPassword());

    }
    public UserDto changePassword(String newPassword,String userName){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRepository.findByUsername(userName)
                .map(user -> {user.setPassword(passwordEncoder.encode(newPassword));
                    return userRepository.save(user);})
                .orElseThrow(()->new UsernameNotFoundException(userName)),UserDto.class);
    }
}
