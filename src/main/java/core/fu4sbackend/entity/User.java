package core.fu4sbackend.entity;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.constant.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
public class User implements UserDetails {
    @Id
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<TestResult> testResults;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
