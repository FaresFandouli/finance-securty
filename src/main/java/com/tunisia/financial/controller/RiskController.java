package com.tunisia.financial.controller;

import com.tunisia.financial.ai.risk.CreditRiskService;
import com.tunisia.financial.dto.response.RiskAssessment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk")
@RequiredArgsConstructor
public class RiskController {

    private final CreditRiskService riskService;

    @GetMapping("/assess/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCIAL_ANALYST')")
    public RiskAssessment assessRisk(@PathVariable Long userId) {
        return riskService.assessRisk(userId);
    }
}