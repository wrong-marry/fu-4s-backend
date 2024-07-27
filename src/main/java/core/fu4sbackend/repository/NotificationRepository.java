package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("select n from Notification n where n.user.username = ?1 order by n.time desc")
    List<Notification> getAllByUsername(String username, Pageable pageable);

    @Query("SELECT n FROM Notification n WHERE n.user.username = :username AND n.isSeen = :seen order by n.time desc")
    List<Notification> getAllByUsernameAndSeen(String username, boolean seen, Pageable pageable);
}
