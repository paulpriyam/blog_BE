package com.example.blog.repository;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.payload.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    Page<Post> findAllByUser(Users user, Pageable pageable);

    Page<Post> findAllByCategory(Category category, Pageable pageable);

    List<Post> findByPostTitleContaining(String postTitle);

    @Query("select p from Post p where p.postTitle like :key or p.postContent like :key")
    List<Post> searchByTileOrDescription(@Param("key") String keyword);
}
