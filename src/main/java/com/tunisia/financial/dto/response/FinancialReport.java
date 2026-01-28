package com.tunisia.financial.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record FinancialReport(
        String summary,
        List<String> recommendations,
        List<String> riskFactors,
        String marketOutlook,
        LocalDateTime generatedAt
) {}
