package com.example.blog.service;

import com.example.blog.entity.Category;
import com.example.blog.exemptions.ResourceNotFoundException;
import com.example.blog.payload.CategoryDto;
import com.example.blog.repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        if (Objects.nonNull(categoryDto.getCategoryTitle()) && !"".equalsIgnoreCase(categoryDto.getCategoryTitle())) {
            category.setCategoryTitle(categoryDto.getCategoryTitle());
        }

        if (Objects.nonNull(categoryDto.getCategoryDescription()) && !"".equalsIgnoreCase(categoryDto.getCategoryDescription())) {
            category.setCategoryDescription(categoryDto.getCategoryDescription());
        }
        Category updatedCategory = categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepo.delete(category);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = categoryRepo.findAll();
        return allCategories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
