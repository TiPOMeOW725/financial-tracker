package com.tipomeow.financial_tracker.entity;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(name = "description", length = 100)
    private String description;
    @Column(name = "amount", precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal amount;
    @Column(name = "time", nullable = false)
    private Instant time;
}
