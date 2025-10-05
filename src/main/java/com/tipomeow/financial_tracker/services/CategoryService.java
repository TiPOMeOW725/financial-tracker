package com.tipomeow.financial_tracker.services;

import com.tipomeow.financial_tracker.dto.CategoryDto;
import com.tipomeow.financial_tracker.dto.CategoryRequestDto;
import com.tipomeow.financial_tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequestDto request);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto updateCategory(Long id, CategoryRequestDto request);
    void deleteCategory(Long id);
}