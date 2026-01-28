package com.tunisia.financial.ai.risk;

import com.tunisia.financial.dto.response.RiskAssessment;

public interface CreditRiskService {
    RiskAssessment assessRisk(Long userId);
}
