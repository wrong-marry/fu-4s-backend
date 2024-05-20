package core.fu4sbackend.security;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public User signup(RegisterDTO input) {
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

        return userRepository.save(user);
    }

    public String authenticate(LoginDTO input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        System.out.println(2);
        if(authentication.isAuthenticated()) {
            User u = User
                    .builder()
                    .username(input.getUsername())
                    .password(passwordEncoder.encode(input.getPassword()))
                    .build();

            return jwtService.generateToken(u);
        }
        else throw new UsernameNotFoundException("Invalid username or password");
    }
}
