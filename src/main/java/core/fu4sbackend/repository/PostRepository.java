package core.fu4sbackend.repository;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.Subject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where p.user.username = ?1")
    List<Post> getAllByUsername(String username, Pageable pageable);

    @Query("select p from Post p where p.status = ?1")
    List<Post> getAllPostByStatus(PostStatus status, Pageable pageable);

    @Query("SELECT p from Post p WHERE p.subject.code = :subjectCode")
    List<PostDto> findBySubjectCode(@Param("subjectCode") String subjectCode, Pageable pageable);

//    List<Post> findBySubject(Subject subject);
    List<Post> findBySubject(Subject subject, Pageable pageable);

}
