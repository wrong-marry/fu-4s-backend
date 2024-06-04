package core.fu4sbackend.service;

import core.fu4sbackend.dto.PostDto;
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

    //NOT IMPLEMENTED: SEARCH FILTER WITH USERNAME
    public List<PostDto> findAllByCriteria(SearchRequest searchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        List<Predicate> predicates = preparePredicates(searchRequest, root, cb);
        cq.where(predicates.toArray(new Predicate[0]));

        switch (searchRequest.getOrder()) {
            case DATE_ASC -> cq.orderBy(cb.asc(root.get("postTime")));
            case TITLE_ASC -> cq.orderBy(cb.asc(root.get("title")));
            case TITLE_DESC -> cq.orderBy(cb.desc(root.get("title")));
            case USERNAME_DESC -> cq.orderBy(cb.desc(root.get("username")));
            case USERNAME_ASC -> cq.orderBy(cb.asc(root.get("username")));
            case null, default -> cq.orderBy(cb.desc(root.get("postTime")));
        }

        if (searchRequest.getCurrentPage() == null) searchRequest.setCurrentPage(0);
        if (searchRequest.getPageSize() == null) searchRequest.setPageSize(1);

        TypedQuery<Post> query = em.createQuery(cq).setMaxResults(searchRequest.getPageSize())
                .setFirstResult(searchRequest.getCurrentPage() * searchRequest.getPageSize());
        List<PostDto> result = new ArrayList<>();
        for (Post post : query.getResultList()) {
            result.add(mapPostDto(post));
        }
        return result;
    }

    public Integer countAllByCriteria(SearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        List<Predicate> predicates = preparePredicates(searchRequest, root, criteriaBuilder);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList().size();
    }

    private List<Predicate> preparePredicates(SearchRequest searchRequest, Root<Post> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (searchRequest.getSemester() != null) {
            Predicate semesterPredicate = criteriaBuilder.equal(root.get("subject").get("semester"), searchRequest.getSemester());
            predicates.add(semesterPredicate);
        }
        if (searchRequest.getTitle() != null) {
            Predicate titlePredicate = criteriaBuilder.like(root.get("title"), "%" + searchRequest.getTitle() + "%");
            predicates.add(titlePredicate);
        }
        if (searchRequest.getSubjectCode() != null) {
            Predicate subjectCodePredicate = criteriaBuilder.like(root.get("subject").get("code"), "%" + searchRequest.getSubjectCode() + "%");
            predicates.add(subjectCodePredicate);
        }
        if (searchRequest.getPostTime() != null) {
            Predicate timePredicate = criteriaBuilder.greaterThan(root.get("postTime"), searchRequest.getPostTime());
            predicates.add(timePredicate);
        }
        if (searchRequest.getIsTest() != null) {
            Predicate testPredicate;
            if (searchRequest.getIsTest())
                testPredicate = criteriaBuilder.isTrue(root.get("isTest"));
            else testPredicate = criteriaBuilder.isFalse(root.get("isTest"));
            predicates.add(testPredicate);
        }
        return predicates;
    }

    private PostDto mapPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setPostTime(post.getPostTime());
        postDto.setUsername(post.getUser().getUsername());
        postDto.setSubjectCode(post.getSubject().getCode());
        postDto.setTest(post.isTest());
        return postDto;
    }

    public List<PostDto> getAllByUsername(String username) {
        List<Post> list = postRepository.getAllByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        return list
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    public PostDto getById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) return null;
        return mapPostDto(post);
    }
}
