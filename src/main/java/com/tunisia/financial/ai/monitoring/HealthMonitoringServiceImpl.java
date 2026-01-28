package com.tunisia.financial.ai.monitoring;

import com.tunisia.financial.dto.response.HealthAlert;
import com.tunisia.financial.dto.response.SystemMetrics;
import com.tunisia.financial.enums.HealthStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class HealthMonitoringServiceImpl implements HealthMonitoringService {

    @Override
    public SystemMetrics collectMetrics() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        Runtime runtime = Runtime.getRuntime();

        double cpuUsage = osBean.getSystemLoadAverage() * 100;
        double memoryUsage = (double) (runtime.totalMemory() - runtime.freeMemory()) / runtime.maxMemory() * 100;

        return new SystemMetrics(
                cpuUsage > 0 ? cpuUsage : 15.0,
                memoryUsage,
                50.0, 10, 100.0, 0.5,
                LocalDateTime.now()
        );
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public HealthAlert analyzeHealth() {
        SystemMetrics metrics = collectMetrics();
        HealthStatus status = determineHealthStatus(metrics);
        List<String> recommendations = generateRecommendations(status, metrics);
        String message = buildHealthMessage(status, metrics);

        if (status != HealthStatus.HEALTHY) {
            log.warn("Health issue detected: {}", message);
        }

        return new HealthAlert(status, message, recommendations, metrics);
    }

    private HealthStatus determineHealthStatus(SystemMetrics metrics) {
        if (metrics.cpuUsage() > 90 || metrics.memoryUsage() > 90) return HealthStatus.CRITICAL;
        if (metrics.cpuUsage() > 70 || metrics.memoryUsage() > 70) return HealthStatus.WARNING;
        return HealthStatus.HEALTHY;
    }

    private String buildHealthMessage(HealthStatus status, SystemMetrics metrics) {
        return String.format("System %s: CPU %.1f%%, Memory %.1f%%", 
                status.name(), metrics.cpuUsage(), metrics.memoryUsage());
    }

    private List<String> generateRecommendations(HealthStatus status, SystemMetrics metrics) {
        if (status == HealthStatus.CRITICAL) {
            return Arrays.asList("Immediate action required", "Check resource-intensive processes");
        }
        if (status == HealthStatus.WARNING) {
            return Arrays.asList("Monitor system closely");
        }
        return Arrays.asList("System operating normally");
    }
}
