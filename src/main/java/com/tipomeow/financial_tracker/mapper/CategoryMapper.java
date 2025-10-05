package com.tipomeow.financial_tracker.mapper;

import com.tipomeow.financial_tracker.dto.CategoryDto;
import com.tipomeow.financial_tracker.dto.CategoryRequestDto;
import com.tipomeow.financial_tracker.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toDtoList(List<Category> categories);
    Category toEntity(CategoryRequestDto requestDto);
}
