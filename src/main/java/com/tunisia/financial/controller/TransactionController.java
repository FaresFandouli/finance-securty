package com.tunisia.financial.controller;

import com.tunisia.financial.dto.request.TransactionRequest;
import com.tunisia.financial.dto.response.TransactionResponse;
import com.tunisia.financial.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('SME_USER', 'FINANCIAL_ANALYST')")
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request,
            Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.createTransaction(request, userEmail));
    }

    @GetMapping("/gettransaction/{id}")
    @PreAuthorize("hasAnyRole('SME_USER', 'FINANCIAL_ANALYST', 'ADMIN')")
    public ResponseEntity<TransactionResponse> getTransaction(
            @PathVariable Long id,
            Authentication authentication) {
        return ResponseEntity.ok(
                transactionService.getTransaction(id, authentication.getName())
        );
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('FINANCIAL_ANALYST', 'ADMIN')")
    public ResponseEntity<Page<TransactionResponse>> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(transactionService.getAllTransactions(page, size));
    }

    @GetMapping("/my-transactions")
    public ResponseEntity<List<TransactionResponse>> getMyTransactions(Authentication authentication) {
        return ResponseEntity.ok(
                transactionService.getUserTransactions(authentication.getName())
        );
    }
}
