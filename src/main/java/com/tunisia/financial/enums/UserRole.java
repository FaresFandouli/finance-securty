package com.tunisia.financial.enums;

/**
 * User roles in the system
 */
public enum UserRole {
    ADMIN("Administrator with full system access"),
    FINANCIAL_ANALYST("Financial analyst with analytical capabilities"),
    SME_USER("Small and Medium Enterprise user"),
    AUDITOR("System auditor with read-only access");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
