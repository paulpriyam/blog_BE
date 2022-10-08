package com.example.blog.service;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.exemptions.ResourceNotFoundException;
import com.example.blog.payload.PostDto;
import com.example.blog.repository.CategoryRepo;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        Post createdPost = this.modelMapper.map(postDto, Post.class);
        createdPost.setPostImage("default.png");
        createdPost.setPostDate(new Date());
        createdPost.setUser(user);
        createdPost.setCategory(category);
        Post newPost = postRepository.save(createdPost);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Post postDtoToPost = this.modelMapper.map(postDto, Post.class);
        post.setPostContent(postDtoToPost.getPostContent());
        post.setPostTitle(postDtoToPost.getPostTitle());
        post.setPostDate(new Date());
        Post updatePost = postRepository.save(post);
        return this.modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        postRepository.delete(post);
    }

    @Override
    public Post getPostById(Long postId) {
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public List<PostDto> getAllPOstByUser(Long userId) {
        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPOstByCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
