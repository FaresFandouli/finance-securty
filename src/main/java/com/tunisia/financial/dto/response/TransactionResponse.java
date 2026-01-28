package com.tunisia.financial.dto.response;

import com.tunisia.financial.enums.TransactionStatus;
import com.tunisia.financial.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        TransactionType type,
        TransactionStatus status,
        BigDecimal amount,
        String description,
        Double fraudScore,
        Boolean isFraud,
        LocalDateTime createdAt,
        String merchantName,
        String location
) {}
