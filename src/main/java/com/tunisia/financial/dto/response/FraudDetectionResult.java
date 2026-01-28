package com.tunisia.financial.dto.response;

import java.util.List;

public record FraudDetectionResult(
        boolean isFraud,
        double confidence,
        List<ModelResult> modelResults,
        String reasoning
) {}
