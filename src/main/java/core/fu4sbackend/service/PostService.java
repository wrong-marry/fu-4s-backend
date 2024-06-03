package core.fu4sbackend.service;

import core.fu4sbackend.constant.PostStatus;
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
            System.out.println(searchRequest.getIsTest());
            Predicate testPredicate;
            if (searchRequest.getIsTest())
                testPredicate = cb.isTrue(root.get("isTest"));
            else testPredicate = cb.isFalse(root.get("isTest"));
            predicates.add(testPredicate);
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Post> query = em.createQuery(cq);
        List<PostDto> result = new ArrayList<>();
        for (Post post : query.getResultList()) {
            result.add(mapPostDto(post));
        }
        return result;
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

    public List<PostDto> getAllByPostStatus(PostStatus status, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("postTime").descending());
        List<Post> list = postRepository.getAllPostByStatus(status,paging);

        ModelMapper modelMapper = new ModelMapper();
        return list
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    public PostDto getById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post==null) return null;
        return mapPostDto(post);
    }

    public Integer getNumberOfPosts(String username) {
        return postRepository.getAllByUsername(username, null).size();
    }
    public Integer getNumberOfPostsEachStatus(PostStatus status) {
        return postRepository.getAllPostByStatus(status, null).size();
    }
}
