package com.tunisia.financial.dto.response;

public record ModelResult(
        String modelName,
        double confidence,
        boolean prediction
) {}
