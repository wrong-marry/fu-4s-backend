package core.fu4sbackend.repository;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where p.user.username = ?1")
    List<Post> getAllByUsername(String username, Pageable pageable);


    @Query("select p from Post p where p.user.username = ?1 and p.status = 'ACTIVE'")
    List<Post> getAllActivePostByUsername(String username, Pageable pageable);

    @Query("select p from Post p where p.status = ?1")
    List<Post> getAllPostByStatus(PostStatus status, Pageable pageable);


//    List<Post> findBySubject(Subject subject);
    List<Post> findBySubject(Subject subject, Pageable pageable);

    int countPostsByPostTimeBetween(Date startDate, Date endDate);

    int countPostsByPostTimeBetweenAndIsTest(Date startDate, Date endDate, boolean isTest);

    @Query("SELECT p.subject.code, COUNT(p) FROM Post p WHERE p.postTime BETWEEN :startDate AND :endDate GROUP BY p.subject.code")
    List<Object[]> countPostsByPostTimeBetweenGroupBySubjectCode(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    Optional<Post> findTopByStatusOrderByPostTimeDesc(PostStatus status);

    @Query("SELECT p FROM Post p WHERE " +
            "(:keyword IS NULL OR p.title LIKE %:keyword%) AND " +
            "(:subject IS NULL OR p.subject.code = :subject)")
    List<Post> searchAndFilterPosts(@Param("keyword") String keyword,
                                    @Param("subject") String subject
                                   );

    Page<Post> findBySubjectCodeAndStatus(String code, PostStatus status, Pageable pageable);
}
