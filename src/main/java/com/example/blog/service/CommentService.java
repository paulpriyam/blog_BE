package com.example.blog.service;

import com.example.blog.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Long postId,Long userId);

    CommentDto updateComment(CommentDto commentDto,Long commentId);

    void deleteComment(Long commentId);
}
