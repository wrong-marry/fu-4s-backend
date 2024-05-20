package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value="select * from post where subject_code like '_?1_'", nativeQuery=true)
    List<Post> findBySubject(String subject);
    @Query(value="select * from post where title like '_?1_'", nativeQuery=true)
    List<Post> findByTitle(String title);
}
