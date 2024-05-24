package core.fu4sbackend.service;

import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDto editEmailFirstNameLastName(UserDto newUser,String userName){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRepository.findByUsername(userName)
                .map(user -> {user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return userRepository.save(user);})
                .orElseThrow(()->new UsernameNotFoundException(userName)),UserDto.class);
    }
    public boolean compareOldAndNewPassword(String userName,String newPassword){
        return userRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException(userName)).getPassword()
                .equals(newPassword);
    }
}
