package com.example.blog.repository;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByUser(Users users);

    List<Post> findByCategory(Category category);
}
