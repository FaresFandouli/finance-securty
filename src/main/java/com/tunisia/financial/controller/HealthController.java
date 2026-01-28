package com.tunisia.financial.controller;

import com.tunisia.financial.ai.monitoring.HealthMonitoringService;
import com.tunisia.financial.dto.response.HealthAlert;
import com.tunisia.financial.dto.response.SystemMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private final HealthMonitoringService healthService;

    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public HealthAlert getHealthStatus() {
        return healthService.analyzeHealth();
    }

    @GetMapping("/metrics")
    @PreAuthorize("hasRole('ADMIN')")
    public SystemMetrics getMetrics() {
        return healthService.collectMetrics();
    }
}