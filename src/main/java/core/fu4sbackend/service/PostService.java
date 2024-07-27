package core.fu4sbackend.service;

import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.repository.PostRepository;
import core.fu4sbackend.repository.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository ;
    private final EntityManager em;

    public PostService(PostRepository postRepository, EntityManager em, SubjectRepository subjectRepository) {
        this.postRepository = postRepository;
        this.subjectRepository = subjectRepository;
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

        List<PostDto> result = new ArrayList<>();
        for (Post post : list) {
            result.add(mapPostDto(post));
        }
        return result;
    }

    public List<PostDto> getAllActivePostByUsername(String username, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("postTime").descending());
        List<Post> list = postRepository.getAllActivePostByUsername(username, paging);

        List<PostDto> result = new ArrayList<>();
        for (Post post : list) {
            result.add(mapPostDto(post));
        }
        return result;
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

    public String UsernameById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) return null;
        return post.getUser().getUsername();
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

    public boolean isValidUser(String username, Integer id) {
        return postRepository.findById(id).orElseThrow().getUser().getUsername().equals(username);
    }



    public List<PostDto> getPostsBySubjectCode(String code, Integer offset) {

        int page = offset == null ? 0 : offset - 1;
        int size = PaginationConstant.RECENT_POST_LOAD_SIZE;
        Sort sort = Sort.by("postTime").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postPage = postRepository.findBySubjectCodeAndStatus(code, PostStatus.ACTIVE, pageable);

        List<PostDto> result = new ArrayList<>();
        for (Post post : postPage.getContent()) {
            result.add(mapPostDto(post));
        }
        return result;
    }

    public int getNumberOfPostsThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        Date startDate = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant());

        return postRepository.countPostsByPostTimeBetween(startDate, endDate);}
    public double calculatePercentageChangePost(int oldValue, int newValue) {
        if (oldValue == 0) {
            return newValue > 0 ? 100.0 : 0.0;
        }
        return (newValue * 100.0) / oldValue;
    }

    public int getNumberOfMaterialsThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        Date startDate = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant());

        // Gọi phương thức của repository để đếm số lượng bài post với điều kiện isTest = false
        return postRepository.countPostsByPostTimeBetweenAndIsTest(startDate, endDate, false);
    }

    public int getNumberOfTestsThisMonth() {
        // Xác định thời gian bắt đầu và kết thúc của tháng hiện tại
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        // Chuyển đổi thành đối tượng Date để truy vấn cơ sở dữ liệu
        Date startDate = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant());

        // Gọi phương thức của repository để đếm số lượng bài post với điều kiện isTest = false
        return postRepository.countPostsByPostTimeBetweenAndIsTest(startDate, endDate, true);
    }

    public Map<String, Map<YearMonth, Integer>> getNumberOfPostsPerSubjectLast12Months() {
        try {
            Map<String, Map<YearMonth, Integer>> subjectPostCountMap = new HashMap<>();

            // Lấy danh sách tất cả các môn học
            List<Subject> subjects = subjectRepository.findAll();

            // Khởi tạo Map với tất cả các môn học và 12 tháng gần nhất
            YearMonth currentMonth = YearMonth.now();
            for (Subject subject : subjects) {
                Map<YearMonth, Integer> monthlyPostCounts = new HashMap<>();
                for (int i = 0; i < 12; i++) {
                    YearMonth month = currentMonth.minusMonths(i);
                    monthlyPostCounts.put(month, 0);
                }
                subjectPostCountMap.put(subject.getCode(), monthlyPostCounts);
            }

            // Đếm số bài viết cho từng môn học trong 12 tháng gần nhất
            for (int i = 0; i < 12; i++) {
                YearMonth month = currentMonth.minusMonths(i);
                LocalDateTime startOfMonth = month.atDay(1).atStartOfDay();
                LocalDateTime endOfMonth = month.atEndOfMonth().atTime(23, 59, 59);

                Date startDate = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
                Date endDate = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant());

                List<Object[]> monthlyPostCount = postRepository.countPostsByPostTimeBetweenGroupBySubjectCode(startDate, endDate);

                for (Object[] result : monthlyPostCount) {
                    String subjectCode = (String) result[0];
                    Long count = (Long) result[1];

                    Map<YearMonth, Integer> monthlyCounts = subjectPostCountMap.get(subjectCode);
                    if (monthlyCounts != null) {
                        monthlyCounts.put(month, count.intValue());
                    }
                }
            }

            return subjectPostCountMap;
        } catch (Exception e) {
            logger.error("Error occurred while calculating the number of posts per subject for the last 12 months: ", e);
            throw new RuntimeException("Failed to calculate post statistics", e);
        }
    }

    public PostDto getMostRecentPendingApprovedPost() {
        try{
            Post post = postRepository.findTopByStatusOrderByPostTimeDesc(PostStatus.valueOf("PENDING_APPROVE")).orElse(null);
            if (post == null) return null;
            return mapPostDto(post);
    } catch (Exception e) {
        // Log chi tiết lỗi
        e.printStackTrace();
        throw e;
    }
        }

    public void restoredPost(Integer postid) {
            Post post = postRepository.findById(postid).orElse(null);

            if (post == null) {
                // Handle case where post does not exist
                throw new IllegalArgumentException("Post not found");
            }
            if (post.getStatus() == PostStatus.HIDDEN) {
                post.setStatus(PostStatus.ACTIVE);
                postRepository.save(post);
            } else {
                // Handle case where post is not in PENDING_APPROVED status
                throw new IllegalStateException("Maybe this post has been pending by another staff");
            }
        }

    public void hidenPost(Integer postid) {
        Post post = postRepository.findById(postid).orElse(null);

        if (post == null) {
            // Handle case where post does not exist
            throw new IllegalArgumentException("Post not found");
        }
        if (post.getStatus() == PostStatus.ACTIVE) {
            post.setStatus(PostStatus.HIDDEN);
            postRepository.save(post);
        } else {
            // Handle case where post is not in PENDING_APPROVED status
            throw new IllegalStateException("Maybe this post has been pending by another staff");
        }
    }

    public void deletedPost(Integer postid) {
        Post post = postRepository.findById(postid).orElse(null);

        if (post == null) {
            // Handle case where post does not exist
            throw new IllegalArgumentException("Post not found");
        }
        if (post.getStatus() == PostStatus.HIDDEN) {
            postRepository.delete(post);
        } else {
            // Handle case where post is not in PENDING_APPROVED status
            throw new IllegalStateException("Maybe this post has been pending by another staff");
        }
    }
    public List<PostDto> searchAndFilterPosts(String keyword, String subject) {
        List<Post> posts = postRepository.searchAndFilterPosts(keyword, subject); List<PostDto> postDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        postDtos = posts
                .stream()
                .map(post  -> {
                    PostDto postDto =  modelMapper.map(post, PostDto.class);
                    return postDto ;
                })
                .collect(Collectors.toList());
        return postDtos;
    }

    public int countPostsBySubjectCode(String code) {
            if (!subjectRepository.existsByCode(code)) return -1;
            Page<Post> posts = postRepository.findBySubjectCodeAndStatus(code, PostStatus.ACTIVE, Pageable.unpaged());
            return (int) posts.getTotalElements();
    }
}
