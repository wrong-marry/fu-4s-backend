package core.fu4sbackend.repository;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    @Query("select p from User p where p.role = ?1")
    List<User> getAllByUserRole(UserRole userrole, Pageable pageable);
}
