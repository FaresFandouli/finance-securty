package com.tunisia.financial.enums;

/**
 * Risk category enumeration with score ranges
 */
public enum RiskCategory {
    LOW(0.0, 0.3, "Low risk"),
    MEDIUM(0.3, 0.6, "Medium risk"),
    HIGH(0.6, 0.8, "High risk"),
    CRITICAL(0.8, 1.0, "Critical risk");

    private final double minScore;
    private final double maxScore;
    private final String description;

    RiskCategory(double minScore, double maxScore, String description) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.description = description;
    }

    public static RiskCategory fromScore(double score) {
        for (RiskCategory category : values()) {
            if (score >= category.minScore && score < category.maxScore) {
                return category;
            }
        }
        return CRITICAL;
    }

    public double getMinScore() {
        return minScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public String getDescription() {
        return description;
    }
}
