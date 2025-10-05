package com.tipomeow.financial_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tipomeow.financial_tracker.entity.Category;
import com.tipomeow.financial_tracker.entity.CategoryType;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    List<Category> findByType(CategoryType type);
}
