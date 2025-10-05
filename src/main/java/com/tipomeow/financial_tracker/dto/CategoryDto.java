package com.tipomeow.financial_tracker.dto;

import com.tipomeow.financial_tracker.entity.CategoryType;
import lombok.*;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private CategoryType type;
}
