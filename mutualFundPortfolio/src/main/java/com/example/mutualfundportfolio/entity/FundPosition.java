package com.example.mutualfundportfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
@Entity
@Data
public class FundPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String fundName;

    private BigDecimal units;

    private BigDecimal cashAmount;

    private String currency;

    public FundPosition( Long userId, String fundName, BigDecimal units, BigDecimal cashAmount, String currency) {

        this.userId = userId;
        this.fundName = fundName;
        this.units = units;
        this.cashAmount = cashAmount;
        this.currency = currency;
    }
}
