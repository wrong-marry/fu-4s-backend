package core.fu4sbackend.service;

import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final EntityManager em;

    public PostService(PostRepository postRepository, EntityManager em) {
        this.postRepository = postRepository;
        this.em = em;
    }

    public List<PostDto> findAllByCriteria(SearchRequest searchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<Post> root = cq.from(Post.class);
        if (searchRequest.getTitle() != null) {
            Predicate titlePredicate = cb.like(root.get("title"), "%" + searchRequest.getTitle() + "%");
            predicates.add(titlePredicate);
        }
        if (searchRequest.getSubjectCode() != null) {
            Predicate subjectCodePredicate = cb.like(root.get("subject").get("code"), "%" + searchRequest.getSubjectCode() + "%");
            predicates.add(subjectCodePredicate);
        }
        if (searchRequest.getPostTime() != null) {
            Predicate timePredicate = cb.greaterThan(root.get("postTime"), searchRequest.getPostTime());
            predicates.add(timePredicate);
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Post> query = em.createQuery(cq);
        List<PostDto> result = new ArrayList<>();
        for (Post post : query.getResultList()) {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setTitle(post.getTitle());
            postDto.setPostTime(post.getPostTime());
            postDto.setUsername(post.getUser().getUsername());
            postDto.setSubjectCode(post.getSubject().getCode());
            postDto.setTest(post.isTest());
            result.add(postDto);
        }
        return result;
    }
}
