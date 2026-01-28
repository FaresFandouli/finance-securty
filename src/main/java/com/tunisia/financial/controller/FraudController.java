package com.tunisia.financial.controller;

import com.tunisia.financial.ai.fraud.FraudDetectionService;
import com.tunisia.financial.dto.request.TransactionRequest;
import com.tunisia.financial.dto.response.FraudDetectionResult;
import com.tunisia.financial.entity.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud")
@RequiredArgsConstructor
@Tag(name = "Fraud Detection", description = "AI-powered fraud detection using DJL and ONNX models")
@SecurityRequirement(name = "Bearer Authentication")
public class FraudController {

    private final FraudDetectionService fraudService;

    @PostMapping("/detect")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCIAL_ANALYST', 'AUDITOR')")
    @Operation(
            summary = "Detect fraud in transaction",
            description = "Analyze transaction using ensemble AI (DJL + ONNX) to detect potential fraud"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraud analysis completed successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions")
    })
    public FraudDetectionResult detectFraud(@RequestBody TransactionRequest request) {
        Transaction transaction = Transaction.builder()
                .amount(request.amount())
                .type(request.type())
                .description(request.description())
                .build();

        return fraudService.detectFraud(transaction);
    }
}
