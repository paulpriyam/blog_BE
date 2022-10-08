package com.example.blog.service;

import com.example.blog.entity.Post;
import com.example.blog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);

    List<PostDto> getAllPOstByUser(Long userId);

    List<PostDto> getAllPOstByCategory(Long categoryId);
}
