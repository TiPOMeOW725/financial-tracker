package com.tipomeow.financial_tracker.controller;

import com.tipomeow.financial_tracker.dto.CategoryDto;
import com.tipomeow.financial_tracker.dto.CategoryExpenseSummaryDto;
import com.tipomeow.financial_tracker.dto.CategoryRequestDto;
import com.tipomeow.financial_tracker.services.CategoryService;
import com.tipomeow.financial_tracker.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final TransactionService transactionService;

    public CategoryController(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryRequestDto request) {
        CategoryDto createdCategory = categoryService.createCategory(request);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto request) {
        CategoryDto updatedCategory = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/expenses/summary")
    public ResponseEntity<List<CategoryExpenseSummaryDto>> getCategoryExpenseSummary() {
        List<CategoryExpenseSummaryDto> summary = transactionService.getCategoryExpenseSummary();
        return ResponseEntity.ok(summary);
    }
}