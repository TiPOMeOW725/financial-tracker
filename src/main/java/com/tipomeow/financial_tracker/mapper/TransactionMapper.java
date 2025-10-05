package com.tipomeow.financial_tracker.mapper;

import com.tipomeow.financial_tracker.dto.TransactionDto;
import com.tipomeow.financial_tracker.dto.TransactionRequestDto;
import com.tipomeow.financial_tracker.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface TransactionMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.type", target = "categoryType")
    TransactionDto toDto(Transaction transaction);
    List<TransactionDto> toDtoList(List<Transaction> transactions);
    @Mapping(target = "category", ignore = true)
    Transaction toEntity(TransactionRequestDto requestDto);
}