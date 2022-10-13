package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.exemptions.ResourceNotFoundException;
import com.example.blog.payload.CommentDto;
import com.example.blog.repository.CommentRepo;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setCreatedAt(new Date());
        comment.setPost(post);
        comment.setUser(user);
        Comment createdComment = commentRepo.save(comment);
        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setUpdatedAt(new Date());
        comment.setPost(post);
        comment.setUser(user);
        Comment updatedComment = commentRepo.save(comment);
        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        commentRepo.delete(comment);
    }
}
