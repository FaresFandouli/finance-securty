package com.tunisia.financial.dto.request;

import com.tunisia.financial.enums.TransactionType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Type is required")
        TransactionType type,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description too long")
        String description,

        Long recipientId,
        String merchantName,
        String location
) {}
