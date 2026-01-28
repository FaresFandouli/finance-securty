package com.tunisia.financial.repository;

import com.tunisia.financial.entity.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {
    List<HealthMetric> findByHealthStatus(String status);
    List<HealthMetric> findByCreatedAtAfter(LocalDateTime date);
}
