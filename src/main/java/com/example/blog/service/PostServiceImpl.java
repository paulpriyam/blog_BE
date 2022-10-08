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
    public Post updatePost(PostDto postDto, Long postId) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

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
    public List<Post> getAllPOstByUser(Long userId) {
        return null;
    }

    @Override
    public List<Post> getAllPOstByCategory(Long categoryId) {
        return null;
    }
}
