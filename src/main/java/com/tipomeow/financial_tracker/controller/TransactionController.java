package com.tipomeow.financial_tracker.controller;

import com.tipomeow.financial_tracker.dto.TransactionDto;
import com.tipomeow.financial_tracker.dto.TransactionRequestDto;
import com.tipomeow.financial_tracker.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        TransactionDto transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionRequestDto request) {
        TransactionDto createdTransaction = transactionService.createTransaction(request);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequestDto request) {
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, request);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<TransactionDto>> findTransactionsByCategory(@PathVariable Long categoryId) {
        List<TransactionDto> transactions = transactionService.getTransactionsByCategory(categoryId);
        return ResponseEntity.ok(transactions);
    }
}