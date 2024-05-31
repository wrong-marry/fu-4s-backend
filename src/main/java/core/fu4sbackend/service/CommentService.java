package core.fu4sbackend.service;

import core.fu4sbackend.constant.CommentStatus;
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

    public List<CommentDto> findByPostId(Integer postId) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            commentDto.setUsername(comment.getUser().getFirstName() + comment.getUser().getLastName());
            commentDto.setAccount(comment.getUser().getUsername());
            return commentDto;
        }).filter(comment -> comment.getStatus() == CommentStatus.ACTIVE).toList();
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
        c.setDate(commentDto.getDate());
        c.setStatus(CommentStatus.ACTIVE);
        commentRepository.save(c);
        return 0;
    }

    public List<CommentDto> staffFindByPostId(Integer postId) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            commentDto.setUsername(comment.getUser().getFirstName() + comment.getUser().getLastName());
            commentDto.setAccount(comment.getUser().getUsername());
            return commentDto;
        }).toList();
    }

    public int update(Integer id, String commentContent) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c == null) return -1;
        c.setContent(commentContent);
        commentRepository.save(c);
        return 0;
    }
}
