package core.fu4sbackend.service;

import core.fu4sbackend.constant.CommentStatus;
import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.entity.Comment;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.CommentRepository;
import core.fu4sbackend.repository.PostRepository;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDto findById(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) return null;
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        commentDto.setUsername(comment.getUser().getFirstName() + comment.getUser().getLastName());
        commentDto.setAccount(comment.getUser().getUsername());
        return commentDto;
    }

    public List<CommentDto> findByPostId(Integer postId, Integer offset, Boolean isStaff, Boolean sorted) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        List<Comment> comments = sorted ? commentRepository.findByPostIdOrderByTime(postId) : commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> {
                    CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
                    commentDto.setUsername(comment.getUser().getFirstName() + comment.getUser().getLastName());
                    commentDto.setAccount(comment.getUser().getUsername());
                    commentDto.setChildrenNumber(countChildren(comment.getId()));
                    return commentDto;
                }).filter(comment -> isStaff || comment.getStatus() == CommentStatus.ACTIVE)
                .skip(offset).limit(PaginationConstant.COMMENT_LOAD_SIZE).toList();
    }

    @Transactional
    public int save(CommentDto commentDto, Integer postId) {
        Comment c = new Comment();
        User u = userRepository.findByUsername(commentDto.getUsername()).orElse(null);
        Post p = postRepository.findById(postId).orElse(null);
        if (u == null) return -1;
        if (p == null) return -2;
        c.setUser(u);
        c.setPost(p);
        c.setContent(commentDto.getContent());
        c.setDate(new Date());
        c.setStatus(CommentStatus.ACTIVE);
        return commentRepository.save(c).getId();
    }

    public int update(Integer id, String commentContent) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c == null) return -1;
        c.setContent(commentContent);
        commentRepository.save(c);
        return 0;
    }

    @Transactional
    public int delete(Integer id) {
        try {
            for (Comment c : commentRepository.findAllByParentId(id)) {
                delete(c.getId());
            }
            commentRepository.deleteById(id);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public int updateStatus(Integer id) {
        try {
            Comment c = commentRepository.findById(id).orElse(null);
            if (c == null) return -1;
            if (c.getStatus() == CommentStatus.ACTIVE)
                c.setStatus(CommentStatus.HIDDEN);
            else c.setStatus(CommentStatus.ACTIVE);
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
        c.setContent(commentDto.getContent());
        c.setDate(new Date());
        c.setStatus(CommentStatus.ACTIVE);
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
}
