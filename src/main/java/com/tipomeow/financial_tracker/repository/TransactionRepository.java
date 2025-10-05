package com.tipomeow.financial_tracker.repository;

import com.tipomeow.financial_tracker.entity.Transaction;
import com.tipomeow.financial_tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository <Transaction, Long>{
    List<Transaction> findByCategory(Category category);
    List<Transaction> findByCategoryId(Long categoryId);
    Boolean existsByCategoryId(Long categoryId);
    List<Transaction> findAllByOrderByTimeDesc();
}
