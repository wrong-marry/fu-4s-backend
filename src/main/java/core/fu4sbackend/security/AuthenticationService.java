package core.fu4sbackend.security;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public JSONObject signup(RegisterDTO input) {
        //System.out.println(userRepository.findByUsername(input.getUsername()).get());
        if(userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new UsernameNotFoundException("Username is already in use");
        }
        else if (!StringUtils.hasText(input.getUsername())||input.getUsername().length()<6) throw new InvalidParameterException("Username is empty or too short");
        else if (!StringUtils.hasText(input.getPassword())||input.getPassword().length()<6) throw new InvalidParameterException("Password is too short");
        else if (!StringUtils.hasText(input.getEmail())||!input.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{1,4}$")) throw new InvalidParameterException("Invalid email");
  
        LocalDateTime enrolledDate = LocalDateTime.now();
        User user = User.builder()
                .username(input.getUsername())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .password(passwordEncoder.encode(input.getPassword()))
                .enrolledDate(enrolledDate)
                .build();

        userRepository.save(user);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(user.getUsername());
        loginDTO.setPassword(input.getPassword());

        return this.authenticate(loginDTO);
    }

    public JSONObject authenticate(LoginDTO input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        if(authentication.isAuthenticated()) {
            User u = userRepository.findByUsername(input.getUsername()).orElseThrow();

            if(u.getStatus() == UserStatus.BANNED) throw new IllegalStateException("Account is banned");

            Map<String, String> res = new HashMap<>();
            res.put("username", u.getUsername());
            res.put("token", jwtService.generateToken(u));
            res.put("role", u.getRole().toString());
            return new JSONObject(res);
        }
        else throw new UsernameNotFoundException("Invalid username or password");
    }
}
