package com.example.blog.service;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.exemptions.ResourceNotFoundException;
import com.example.blog.payload.PostDto;
import com.example.blog.payload.PostPagingResponse;
import com.example.blog.repository.CategoryRepo;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostPagingResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = postRepository.findAll(p);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostPagingResponse postPagingResponse = new PostPagingResponse();
        postPagingResponse.setData(postDtos);
        postPagingResponse.setPageNumber(postPage.getNumber());
        postPagingResponse.setPageSize(postPage.getSize());
        postPagingResponse.setTotalPages(postPage.getTotalPages());
        postPagingResponse.setTotalElements(postPage.getTotalElements());
        postPagingResponse.setLastPage(postPage.isLast());
        return postPagingResponse;
    }

    @Override
    public PostPagingResponse getAllPOstByUser(Long userId, Integer pageNumber, Integer pageSize) {
        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> postPage = postRepository.findAllByUser(user, pageable);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostPagingResponse postPagingResponse = new PostPagingResponse();
        postPagingResponse.setData(postDtos);
        postPagingResponse.setPageNumber(postPage.getNumber());
        postPagingResponse.setPageSize(postPage.getSize());
        postPagingResponse.setTotalPages(postPage.getTotalPages());
        postPagingResponse.setTotalElements(postPage.getTotalElements());
        postPagingResponse.setLastPage(postPage.isLast());
        return postPagingResponse;
    }

    @Override
    public PostPagingResponse getAllPOstByCategory(Long categoryId, Integer pageNumber, Integer pageSize) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> postPage = postRepository.findAllByCategory(category, pageable);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostPagingResponse postPagingResponse = new PostPagingResponse();
        postPagingResponse.setData(postDtos);
        postPagingResponse.setPageNumber(postPage.getNumber());
        postPagingResponse.setPageSize(postPage.getSize());
        postPagingResponse.setTotalPages(postPage.getTotalPages());
        postPagingResponse.setTotalElements(postPage.getTotalElements());
        postPagingResponse.setLastPage(postPage.isLast());
        return postPagingResponse;
    }

    @Override
    public List<PostDto> findPostByTitle(String keyword) {
        List<Post> posts = postRepository.findByPostTitleContaining(keyword);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findByTitleOrContent(String keyword) {
        List<Post> posts = postRepository.searchByTileOrDescription("%" + keyword + "%");
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
