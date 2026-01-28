package com.tunisia.financial.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Fraud Pattern Entity for storing detected fraud patterns
 */
@Entity
@Table(name = "fraud_patterns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudPattern extends BaseEntity {

    @Column(nullable = false)
    private String pattern;

    @Column(length = 1000)
    private String description;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(name = "detection_count")
    private Integer detectionCount = 0;

    @Column(name = "status")
    private String severity;
}
