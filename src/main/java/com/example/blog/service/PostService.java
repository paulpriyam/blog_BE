package com.example.blog.service;

import com.example.blog.entity.Post;
import com.example.blog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    Post updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    Post getPostById(Long postId);

    List<Post> getAllPost();

    List<Post> getAllPOstByUser(Long userId);

    List<Post> getAllPOstByCategory(Long categoryId);
}
