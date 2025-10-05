package com.tipomeow.financial_tracker.dto;

import com.tipomeow.financial_tracker.entity.CategoryType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionDto {
    Long id;
    String description;
    BigDecimal amount;
    Instant time;

    Long categoryId;
    String categoryName;
    CategoryType categoryType;
}
