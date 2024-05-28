package core.fu4sbackend.service;

import core.fu4sbackend.dto.CommentDto;
import core.fu4sbackend.entity.Comment;
import core.fu4sbackend.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto findById(long id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) return null;
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        commentDto.setUsername(comment.getUser().getFirstName()+comment.getUser().getLastName());
        return commentDto;
    }

    public List<CommentDto> findByPostId(long postId) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CommentDto.class, Comment.class);
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            commentDto.setUsername(comment.getUser().getFirstName()+comment.getUser().getLastName());
            return commentDto;
        }).toList();
    }
}
