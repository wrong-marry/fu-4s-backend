package core.fu4sbackend.service;

import core.fu4sbackend.constant.CommentStatus;
import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.entity.Comment;
import core.fu4sbackend.entity.Notification;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.CommentRepository;
import core.fu4sbackend.repository.NotificationRepository;
import core.fu4sbackend.repository.PostRepository;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public CommentService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<CommentDto> findByPostId(Integer postId, Integer offset, Boolean isStaff, Boolean sorted) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        List<Comment> comments = sorted ? commentRepository.findByPostIdOrderByTime(postId) : commentRepository.findByPostId(postId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return comments.stream().map(comment -> {
                    CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
                    commentDto.setUsername(comment.getUser().getFirstName() + comment.getUser().getLastName());
                    commentDto.setAccount(comment.getUser().getUsername());
                    commentDto.setChildrenNumber(countChildren(comment.getId()));
                    return commentDto;
                }).filter(comment -> isStaff || comment.getStatus() == CommentStatus.ACTIVE || comment.getAccount().equals(username))
                .skip(offset).limit(PaginationConstant.COMMENT_LOAD_SIZE).toList();
    }

    @Transactional
    public int save(CommentDto commentDto, Integer postId) throws Exception {
        if (!StringUtils.hasLength(commentDto.getContent())) {
            throw new InvalidParameterException("Empty content not allowed");
        }
        Comment c = new Comment();
        User u = userRepository.findByUsername(commentDto.getUsername()).orElse(null);
        Post p = postRepository.findById(postId).orElse(null);
        if (u == null) return -1;
        if (p == null) return -2;
        c.setUser(u);
        if (!p.getStatus().equals(PostStatus.ACTIVE)) throw new InvalidParameterException("Cannot comment on inactive post");
        c.setPost(p);
        c.setContent(commentDto.getContent());
        c.setDate(new Date());
        c.setStatus(CommentStatus.ACTIVE);

        Notification newNotification = new Notification();
        User getter = p.getUser();
        if (!getter.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        {
            newNotification.setUser(getter);
            newNotification.setSeen(false);
            newNotification.setTime(new Date());
            newNotification.setPostId(p.getId());
            newNotification.setMessage("'" +c.getUser().getFirstName()  + c.getUser().getLastName() + "' commented on your post");
            notificationRepository.save(newNotification);
        }
        return commentRepository.save(c).getId();
    }

    public int update(Integer id, String commentContent) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c == null) return -1;
        if (!StringUtils.hasLength(commentContent)) {
            throw new InvalidParameterException("Empty content not allowed");
        }
        c.setContent(commentContent);
        commentRepository.save(c);
        return 0;
    }

    @Transactional
    public int delete(Integer id) throws Exception {
            for (Comment c : commentRepository.findAllByParentId(id)) {
                if (delete(c.getId())!=0) throw new Exception();
            }
            commentRepository.deleteById(id);
            return 0;
    }

    public int updateStatus(Integer id) {
        try {
            Comment c = commentRepository.findById(id).orElse(null);
            if (c == null) return -1;
            if (c.getStatus() == CommentStatus.ACTIVE) {
                c.setStatus(CommentStatus.HIDDEN);
                //Send notification to the user
                Notification newNotification = new Notification();
                User getter = c.getUser();
                if (!getter.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
                {
                    Comment comment = c;
                    while (comment.getPost()==null) comment = comment.getParent();
                    newNotification.setUser(getter);
                    newNotification.setSeen(false);
                    newNotification.setTime(new Date());
                    newNotification.setPostId(comment.getPost().getId());
                    newNotification.setMessage("'A staff has hidden your comment");
                    notificationRepository.save(newNotification);
                }
            }
            else {
                c.setStatus(CommentStatus.ACTIVE);
                //Send notification to the user
                Notification newNotification = new Notification();
                User getter = c.getUser();
                if (!getter.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
                {
                    Comment comment = c;
                    //DEBUG
                    System.out.println(comment.getPost());
                    while (comment.getPost()==null) comment = comment.getParent();
                    newNotification.setUser(getter);
                    newNotification.setSeen(false);
                    newNotification.setTime(new Date());
                    newNotification.setPostId(comment.getPost().getId());
                    newNotification.setMessage("'A staff has unhidden your comment");
                    notificationRepository.save(newNotification);
                }
            }
            commentRepository.save(c);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    public int saveChild(CommentDto commentDto, int commentId) {
        Comment c = new Comment();
        User u = userRepository.findByUsername(commentDto.getUsername()).orElse(null);
        Comment parent = commentRepository.findById(commentId).orElse(null);
        if (u == null) return -1;
        if (parent == null) return -2;
        c.setUser(u);
        c.setParent(parent);
        if (!StringUtils.hasLength(commentDto.getContent())) {
            throw new InvalidParameterException("Empty content not allowed");
        }
        c.setContent(commentDto.getContent());
        c.setDate(new Date());
        c.setStatus(CommentStatus.ACTIVE);

        Notification newNotification = new Notification();
        User getter = parent.getUser();

        if (!getter.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        {
            newNotification.setUser(getter);
            newNotification.setSeen(false);
            newNotification.setTime(new Date());
            newNotification.setPostId(parent.getPost().getId());
            newNotification.setMessage("'" +c.getUser().getFirstName() + " " + c.getUser().getLastName() + "' replied to your comment");
            notificationRepository.save(newNotification);
        }
        return commentRepository.save(c).getId();
    }

    public List<CommentDto> getAllChildren(int commentId, String offset) {
        int offs = 0;
        if (StringUtils.hasText(offset) && offset.trim().matches("\\d+")) offs = Integer.parseInt(offset);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        return commentRepository.findAllByParentId(commentId).stream().map(comment -> {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            commentDto.setUsername(comment.getUser().getFirstName() + comment.getUser().getLastName());
            commentDto.setAccount(comment.getUser().getUsername());
            commentDto.setChildrenNumber(countChildren(comment.getId()));
            return commentDto;
        }).skip(offs).limit(PaginationConstant.COMMENT_CHILDREN_LOAD_SIZE).toList();
    }

    public Integer countChildren(int commentId) {
        return commentRepository.countByParentId(commentId);
    }


    public Integer getNumberOfComments() {
        return commentRepository.findAll().size();
    }
    public int getNumberOfCommentsThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        Date startDate = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant());

        return commentRepository.countCommentsByDateBetween(startDate, endDate);}
    public double calculatePercentageChangeComment(int oldValue, int newValue) {
        if (oldValue == 0) {
            return newValue > 0 ? 100.0 : 0.0;
        }
        return (newValue * 100.0) / oldValue;
    }
}
