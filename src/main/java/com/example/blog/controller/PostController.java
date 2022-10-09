package com.example.blog.controller;

import com.example.blog.entity.Post;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.PostDto;
import com.example.blog.payload.PostPagingResponse;
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
    private ResponseEntity<PostPagingResponse> getAllPostByUserId(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        PostPagingResponse postDtos = postService.getAllPOstByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}/posts")
    private ResponseEntity<PostPagingResponse> getAllPostByCategoryId(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        PostPagingResponse postDtos = postService.getAllPOstByCategory(categoryId, pageNumber, pageSize);
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

    @GetMapping("posts")
    private ResponseEntity<PostPagingResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        PostPagingResponse postPagingResponse = postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity(postPagingResponse, HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    private ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity(post, HttpStatus.OK);
    }
}
