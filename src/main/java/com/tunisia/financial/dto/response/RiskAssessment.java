package com.tunisia.financial.dto.response;

import com.tunisia.financial.enums.RiskCategory;
import java.util.List;

public record RiskAssessment(
        double riskScore,
        RiskCategory category,
        String analysis,
        List<String> riskFactors,
        List<String> recommendations
) {}
