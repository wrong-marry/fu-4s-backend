package core.fu4sbackend.repository;

import core.fu4sbackend.constant.UserRole;
import core.fu4sbackend.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    @Query("select p from User p where p.role = ?1")
    List<User> getAllByUserRole(UserRole userrole, Pageable pageable);
    @Transactional
    @Modifying
    @Query("delete from User p where  p.username=?1")
    void deleteByUsername(String username);
    int countUsersByEnrolledDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
