package com.tunisia.financial.ai.monitoring;

import com.tunisia.financial.dto.response.HealthAlert;
import com.tunisia.financial.dto.response.SystemMetrics;

public interface HealthMonitoringService {
    SystemMetrics collectMetrics();
    HealthAlert analyzeHealth();
}
