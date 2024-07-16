package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "select comment from Comment comment where comment.post.id = ?1 order by comment.id desc")
    public List<Comment> findByPostIdOrderByTime(Integer id);

    @Query(value = "select comment from Comment comment where comment.post.id = ?1")
    public List<Comment> findByPostId(Integer id);

    @Query(value = "select comment from Comment comment where comment.parent.id = ?1")
    List<Comment> findAllByParentId(int commentId);

    @Query(value = "select comment from Comment comment where comment.parent.id = ?1 order by comment.id desc")
    List<Comment> findAllByParentIdOrderByTime(int commentId);

    @Query(value = "select count(*) from Comment comment where comment.parent.id = ?1")
    Integer countByParentId(Integer parentId);

    int countCommentsByDateBetween(Date startDate, Date endDate);
}
