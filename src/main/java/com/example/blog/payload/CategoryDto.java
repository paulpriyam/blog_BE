package com.example.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long categoryId;

    @NotEmpty(message = "Category title cannot be null or empty!!")
    @Size(min = 4,message = "Category title must be longer than 4 characters!!")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10,message = "Category Description must ge greater than 10 characters!!")
    private String categoryDescription;
}
