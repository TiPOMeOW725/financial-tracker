package com.tipomeow.financial_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExpenseSummaryDto {
    Long categoryId;
    String categoryName;
    BigDecimal totalExpenses;
}
