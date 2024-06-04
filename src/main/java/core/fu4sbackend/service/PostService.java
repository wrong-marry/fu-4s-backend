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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        if (searchRequest.getIsTest() != null) {
            Predicate testPredicate;
            if (searchRequest.getIsTest())
                testPredicate = cb.isTrue(root.get("isTest"));
            else testPredicate = cb.isFalse(root.get("isTest"));
            predicates.add(testPredicate);
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

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
        if (searchRequest.getIsTest() != null) {
            Predicate testPredicate;
            if (searchRequest.getIsTest())
                testPredicate = cb.isTrue(root.get("isTest"));
            else testPredicate = cb.isFalse(root.get("isTest"));
            predicates.add(testPredicate);
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return em.createQuery(cq).getResultList().size();
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

    public List<PostDto> getAllByUsername(String username, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("postTime").descending());
        List<Post> list = postRepository.getAllByUsername(username, paging);

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

    public Integer getNumberOfPosts(String username) {
        return postRepository.getAllByUsername(username, null).size();
    }
}
