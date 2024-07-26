package core.fu4sbackend.service;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.dto.UserPostCountDto;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
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
    public UserPostCountDto getUserDto(User user) {
        int postCount = user.getPosts().size(); // Assuming User entity has a getPosts() method
        return new UserPostCountDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getEnrolledDate(),
                postCount        );
    }

    public UserDto getByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findByUsername(username).orElse(null);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    public List<UserPostCountDto> getAllUsers(Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("username").descending());
        List<User> userList = userRepository.findAll(paging).toList();

        return userList
                .stream()
                .map(this::getUserDto)
                .collect(Collectors.toList());
    }

    public List<UserPostCountDto> getAllByUserRole (UserRole userrole, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("username").descending());
        List<User> list = userRepository.getAllByUserRole(userrole, paging);

        ModelMapper modelMapper = new ModelMapper();
        return list
                .stream()
                .map(this::getUserDto)
                .collect(Collectors.toList());
    }

    public Integer getNumberOfUserByRole(UserRole userrole) {
        //System.out.println(userRepository.getAllByUserRole(userrole, null).size());
        return userRepository.getAllByUserRole(userrole, null).size();
    }

    public Integer getNumberOfUsers() {
        return userRepository.findAll().size();
    }


    public UserDto editEmailFirstNameLastName(UserDto newUser,String userName){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRepository.findByUsername(userName)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return userRepository.save(user);
                })
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

    public void banUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        user.setStatus(UserStatus.BANNED);
        userRepository.save(user);
    }
    public void activateUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }
    public void promoteUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        user.setRole(UserRole.STAFF);
        userRepository.save(user);
    }
    public void demoteUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

    public int getNumberOfUsersThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);
        return userRepository.countUsersByEnrolledDateBetween(startOfMonth, endOfMonth);
    }
    public double calculatePercentageChange(int oldValue, int newValue) {
        if (oldValue == 0) {
            return newValue > 0 ? 100.0 : 0.0;
        }
        return (newValue * 100.0) / oldValue;
    }
}
