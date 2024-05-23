package core.fu4sbackend.service;

import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        User u =userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        return u;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
