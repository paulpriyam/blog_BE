package com.example.blog.service;

import com.example.blog.entity.Post;
import com.example.blog.payload.PostDto;
import com.example.blog.payload.PostPagingResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    PostDto getPostById(Long postId);

    PostPagingResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostPagingResponse getAllPOstByUser(Long userId, Integer pageNumber, Integer pageSize);

    PostPagingResponse getAllPOstByCategory(Long categoryId, Integer pageNumber, Integer pageSize);
}
