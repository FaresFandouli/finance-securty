package com.tunisia.financial.dto.response;

import com.tunisia.financial.enums.HealthStatus;
import java.util.List;

public record HealthAlert(
        HealthStatus status,
        String message,
        List<String> recommendations,
        SystemMetrics metrics
) {}
