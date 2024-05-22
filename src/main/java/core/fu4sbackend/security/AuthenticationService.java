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

        User user = User.builder()
                .username(input.getUsername())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        userRepository.save(user);

        Map<String, String> res = new HashMap<>();
        res.put("username", user.getUsername());
        res.put("firstName", user.getFirstName());
        res.put("lastName", user.getLastName());
        res.put("email", user.getEmail());

        return new JSONObject(res);
    }

    public JSONObject authenticate(LoginDTO input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        if(authentication.isAuthenticated()) {
            User u = User
                    .builder()
                    .username(input.getUsername())
                    .password(passwordEncoder.encode(input.getPassword()))
                    .build();

            Map<String, String> res = new HashMap<>();
            res.put("username", u.getUsername());
            res.put("token", jwtService.generateToken(u));

            return new JSONObject(res);
        }
        else throw new UsernameNotFoundException("Invalid username or password");
    }
}
