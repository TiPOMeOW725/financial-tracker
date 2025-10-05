package com.tipomeow.financial_tracker.services;

import com.tipomeow.financial_tracker.dto.CategoryExpenseSummaryDto;
import com.tipomeow.financial_tracker.dto.TransactionDto;
import com.tipomeow.financial_tracker.dto.TransactionRequestDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionRequestDto request);
    List<TransactionDto> getAllTransactions();
    TransactionDto getTransactionById(Long id);
    TransactionDto updateTransaction(Long id, TransactionRequestDto request);
    void deleteTransaction(Long id);
    List<CategoryExpenseSummaryDto> getCategoryExpenseSummary();
    List<TransactionDto> getTransactionsByCategory(Long categoryId);
    }