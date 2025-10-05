package com.tipomeow.financial_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="type", unique = false, length = 12)
    private CategoryType type;
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name; 
}