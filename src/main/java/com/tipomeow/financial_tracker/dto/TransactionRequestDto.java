package com.tipomeow.financial_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionRequestDto {
    String description;
    BigDecimal amount;
    Long categoryId;
    Instant time;
}
