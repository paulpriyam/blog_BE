package com.example.blog.controller;

import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.PostDto;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{categoryId}/posts")
    private ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId) {
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/posts")
    private ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable("userId") Long userId) {
        List<PostDto> postDtos = postService.getAllPOstByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}/posts")
    private ResponseEntity<List<PostDto>> getAllPostByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<PostDto> postDtos = postService.getAllPOstByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @PutMapping("posts/{postId}")
    private ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Long postId) {
        PostDto updatedPostDto = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    private ApiResponse deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return new ApiResponse("post successfully deleted", true);
    }
}
