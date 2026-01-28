package com.tunisia.financial.dto.response;

import java.time.LocalDateTime;

public record SystemMetrics(
        double cpuUsage,
        double memoryUsage,
        double diskUsage,
        int activeConnections,
        double requestRate,
        double errorRate,
        LocalDateTime timestamp
) {}
