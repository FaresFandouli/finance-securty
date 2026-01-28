package com.tunisia.financial.entity;

import com.tunisia.financial.enums.TransactionStatus;
import com.tunisia.financial.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Transaction Entity representing financial transactions
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user")
public class Transaction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 500)
    private String description;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "fraud_score")
    private Double fraudScore;

    @Column(name = "is_fraud")
    private Boolean isFraud = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "location")
    private String location;
}
