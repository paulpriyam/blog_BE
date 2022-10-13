package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.CommentDto;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comments")
    private ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        CommentDto commentDto1 = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    private ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment successfully deleted", true), HttpStatus.OK);
    }

    @PutMapping("user/{userId}/post/{postId}/comments")
    private ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        CommentDto commentDto1 = commentService.updateComment(commentDto, postId, userId);
        return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }
}
