package com.project.javatesttask.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class InternalCurrencyResponse {

    private BigInteger id;
    private String name;
    private BigDecimal lastPriceInUSD;
    private LocalDateTime createdAt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLastPriceInUSD() {
        return lastPriceInUSD;
    }

    public void setLastPriceInUSD(BigDecimal lastPriceInUSD) {
        this.lastPriceInUSD = lastPriceInUSD;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
