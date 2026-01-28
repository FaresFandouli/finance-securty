package com.tunisia.financial.dto.response;

import java.time.LocalDateTime;

public record DashboardSummary(
        long totalTransactions,
        double totalRevenue,
        long fraudCount,
        double fraudRate,
        double averageRiskScore,
        long highRiskCount,
        String period,
        LocalDateTime lastUpdated
) {}
