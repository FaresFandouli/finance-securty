package com.tunisia.financial.repository;

import com.tunisia.financial.entity.FraudPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudPatternRepository extends JpaRepository<FraudPattern, Long> {
    List<FraudPattern> findBySeverity(String severity);
    List<FraudPattern> findByConfidenceScoreGreaterThan(Double score);
}
