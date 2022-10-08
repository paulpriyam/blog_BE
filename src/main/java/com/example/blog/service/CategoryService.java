package com.example.blog.service;

import com.example.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    CategoryDto getCategoryById(Long categoryId);

    void deleteCategoryById(Long categoryId);

    List<CategoryDto> getAllCategories();
}