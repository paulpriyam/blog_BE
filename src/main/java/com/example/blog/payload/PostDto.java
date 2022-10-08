package com.example.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long postId;
    private String postTitle;
    private String postContent;
    private String postImage;
    private Date postDate;
    private UserDto user;
    private CategoryDto category;
}
