package core.fu4sbackend.service;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.constant.UserStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.User;
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
import java.util.stream.Collectors;

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
        postDto.setUsername(post.getUser().getFirstName()+" "+post.getUser().getLastName());
        postDto.setSubjectCode(post.getSubject().getCode());
        postDto.setTest(post.isTest());
        postDto.setStatus(post.getStatus());
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
        if (post == null) return null;
        return mapPostDto(post);
    }

    public List<PostDto> getAllPosts(Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
        List<Post> postList = postRepository.findAll(paging).toList();
        List<PostDto> postDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        postDtos = postList
                .stream()
                .map(post  -> {
                    PostDto postDto =  modelMapper.map(post, PostDto.class);
                    return postDto ;
                })
                .collect(Collectors.toList());
        return postDtos;
    }

    public Integer getNumberOfPosts(String username) {
        return postRepository.getAllByUsername(username, null).size();
    }

    public Integer getNumberOfAllPosts() {
        return postRepository.findAll().size();
    }

    public Integer getNumberOfPostsEachStatus(PostStatus status) {
        return postRepository.getAllPostByStatus(status, null).size();
    }

    // check if poststatus = pending_approved so setstatus else send a message that "maybe this post haved been pend by another staff"
    public void approvePost(Integer postId) {
        Post post = postRepository.findById(postId).orElse(null);

        if (post == null) {
            // Handle case where post does not exist
            throw new IllegalArgumentException("Post not found");
        }

        if (post.getStatus() == PostStatus.PENDING_APPROVE) {
            post.setStatus(PostStatus.ACTIVE);
            postRepository.save(post);
        } else {
            // Handle case where post is not in PENDING_APPROVED status
            throw new IllegalStateException("Maybe this post has been pending by another staff");
        }
    }

    public void deniedPost(Integer PostId) {
        Post post = postRepository.findById(PostId).orElse(null);

        if (post == null) {
            // Handle case where post does not exist
            throw new IllegalArgumentException("Post not found");
        }
        if (post.getStatus() == PostStatus.PENDING_APPROVE) {
            post.setStatus(PostStatus.HIDDEN);
            postRepository.save(post);
        } else {
            // Handle case where post is not in PENDING_APPROVED status
            throw new IllegalStateException("Maybe this post has been pending by another staff");
        }
    }
}
