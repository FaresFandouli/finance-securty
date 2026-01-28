package com.tunisia.financial.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Health Metric Entity for system monitoring
 */
@Entity
@Table(name = "health_metrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthMetric extends BaseEntity {

    @Column(name = "cpu_usage")
    private Double cpuUsage;

    @Column(name = "memory_usage")
    private Double memoryUsage;

    @Column(name = "disk_usage")
    private Double diskUsage;

    @Column(name = "active_connections")
    private Integer activeConnections;

    @Column(name = "request_rate")
    private Double requestRate;

    @Column(name = "error_rate")
    private Double errorRate;

    @Column(name = "health_status")
    private String healthStatus;

    @Column(length = 1000)
    private String alerts;
}
