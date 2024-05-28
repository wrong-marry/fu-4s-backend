package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select comment from Comment comment where comment.post.id = ?1")
    public List<Comment> findByPostId(long id);
}
