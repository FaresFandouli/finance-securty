package com.tunisia.financial.enums;

/**
 * Transaction types supported by the system
 */
public enum TransactionType {
    PAYMENT("Payment transaction"),
    TRANSFER("Money transfer"),
    WITHDRAWAL("Cash withdrawal"),
    DEPOSIT("Cash deposit");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
