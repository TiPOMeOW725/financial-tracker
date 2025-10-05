package com.tipomeow.financial_tracker.services;

import com.tipomeow.financial_tracker.dto.CategoryExpenseSummaryDto;
import com.tipomeow.financial_tracker.dto.TransactionDto;
import com.tipomeow.financial_tracker.dto.TransactionRequestDto;
import com.tipomeow.financial_tracker.entity.Category;
import com.tipomeow.financial_tracker.entity.CategoryType;
import com.tipomeow.financial_tracker.entity.Transaction;
import com.tipomeow.financial_tracker.exception.ResourceNotFoundException;
import com.tipomeow.financial_tracker.mapper.TransactionMapper;
import com.tipomeow.financial_tracker.repository.CategoryRepository;
import com.tipomeow.financial_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionDto createTransaction(TransactionRequestDto request) {
        if (request.getCategoryId()==null){
            throw new ResourceNotFoundException("Category id cannot be null");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        Category category = optionalCategory.orElseThrow(() -> {
            throw new ResourceNotFoundException("Category not found with id: " + request.getCategoryId());
        });
        Transaction transaction = transactionMapper.toEntity(request);
        transaction.setCategory(category);
        if(request.getTime()==null){
            transaction.setTime(Instant.now());
        }
        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toDto(saved);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAllByOrderByTimeDesc();
        return transactionMapper.toDtoList(transactions);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Transaction not found with id: " + id
                ));
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionRequestDto request) {
        Transaction existingTransaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                "Transaction not found with id: " + id
        ));
        Transaction updated = existingTransaction;
        updated.setDescription(request.getDescription());
        updated.setAmount(request.getAmount());
        updated.setTime(request.getTime());
        if (existingTransaction.getCategory().getId() != request.getCategoryId()){
            Category newCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Category not found with id: " + id
                    ));
            updated.setCategory(newCategory);
        }
        transactionRepository.save(updated);
        return transactionMapper.toDto(updated);
    }

    @Override
    public void deleteTransaction(Long id) {
        if(!transactionRepository.existsById(id)){
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<CategoryExpenseSummaryDto> getCategoryExpenseSummary() {
        List<Category> categoryList = categoryRepository.findByType(CategoryType.EXPENSE);
        List<CategoryExpenseSummaryDto> summaries = categoryList.stream()
                .map(category -> {
                    List<Transaction> transactions = transactionRepository.findByCategory(category);

                    BigDecimal totalExpenses = transactions.stream()
                            .map(Transaction::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return new CategoryExpenseSummaryDto(
                            category.getId(),
                            category.getName(),
                            totalExpenses
                    );
                })
                .sorted(Comparator.comparing(CategoryExpenseSummaryDto::getTotalExpenses).reversed())
                .collect(Collectors.toList());

        return summaries;
    }

    @Override
    public List<TransactionDto> getTransactionsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + categoryId));

        List<Transaction> transactions = transactionRepository.findByCategory(category);

        return transactionMapper.toDtoList(transactions);
    }

}
