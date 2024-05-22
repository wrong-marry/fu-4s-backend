package core.fu4sbackend.service;

import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final EntityManager em;

    public PostService(PostRepository postRepository, EntityManager em) {
        this.postRepository = postRepository;
        this.em = em;
    }

    public List<Post> findAllByCriteria(SearchRequest searchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<Post> root = cq.from(Post.class);
        if (searchRequest.getTitle() != null) {
            Predicate titlePredicate = cb.like(root.get("title"), "%" + searchRequest.getTitle() + "%");
            predicates.add(titlePredicate);
        }
        if (searchRequest.getSubjectCode() != null) {
            Predicate subjectCodePredicate = cb.like(root.get("subjectCode"), "%" + searchRequest.getSubjectCode() + "%");
            predicates.add(subjectCodePredicate);
        }
        if (searchRequest.getPostTime() != null) {
            Predicate timePredicate = cb.greaterThan(root.get("postTime"), searchRequest.getPostTime());
            predicates.add(timePredicate);
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Post> query = em.createQuery(cq);
        return query.getResultList();
    }
}
