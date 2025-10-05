package com.tipomeow.financial_tracker.services;

import com.tipomeow.financial_tracker.dto.CategoryDto;
import com.tipomeow.financial_tracker.dto.CategoryRequestDto;
import com.tipomeow.financial_tracker.entity.Category;
import com.tipomeow.financial_tracker.entity.Transaction;
import com.tipomeow.financial_tracker.exception.BusinessLogicException;
import com.tipomeow.financial_tracker.exception.DuplicateResourceException;
import com.tipomeow.financial_tracker.exception.ResourceNotFoundException;
import com.tipomeow.financial_tracker.mapper.CategoryMapper;
import com.tipomeow.financial_tracker.repository.CategoryRepository;
import com.tipomeow.financial_tracker.repository.TransactionRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryRequestDto request) {
        Optional<Category> existingCategory=categoryRepository.findByName(request.getName());
        if (existingCategory.isPresent()){
            throw new DuplicateResourceException("Category with name " + request.getName() + " already exists");
        }
        Category category = categoryMapper.toEntity(request);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        List<Category> existingCategory=categoryRepository.findAll();
        return categoryMapper.toDtoList(existingCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + id
                ));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryRequestDto request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (!existingCategory.getName().equals(request.getName())) {
            Optional<Category> duplicateCategory = categoryRepository.findByName(request.getName());
            if (duplicateCategory.isPresent()) {
                throw new DuplicateResourceException("Category with name " + request.getName() + " already exists");
            }
        }

        existingCategory.setName(request.getName());
        existingCategory.setType(request.getType());

        Category updated = categoryRepository.save(existingCategory);
        return categoryMapper.toDto(updated);
    }


    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        if (transactionRepository.existsByCategoryId(id)) {
            throw new BusinessLogicException("Cannot delete category with existing transactions");
        }
        categoryRepository.deleteById(id);
    }
}
