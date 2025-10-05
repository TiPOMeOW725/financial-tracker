package com.tipomeow.financial_tracker.dto;

import com.tipomeow.financial_tracker.entity.CategoryType;
import lombok.Data;

@Data
public class CategoryRequestDto {
    String name;
    CategoryType type;
}
