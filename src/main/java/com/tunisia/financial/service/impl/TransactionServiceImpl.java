package com.tunisia.financial.service.impl;

import com.tunisia.financial.dto.request.TransactionRequest;
import com.tunisia.financial.dto.response.FraudDetectionResult;
import com.tunisia.financial.dto.response.TransactionResponse;
import com.tunisia.financial.entity.Transaction;
import com.tunisia.financial.entity.User;
import com.tunisia.financial.enums.TransactionStatus;
import com.tunisia.financial.exception.EntityNotFoundException;
import com.tunisia.financial.repository.TransactionRepository;
import com.tunisia.financial.repository.UserRepository;
import com.tunisia.financial.service.TransactionService;
import com.tunisia.financial.ai.fraud.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final FraudDetectionService fraudDetectionService;

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Transaction transaction = Transaction.builder()
                .type(request.type())
                .amount(request.amount())
                .description(request.description())
                .recipientId(request.recipientId())
                .merchantName(request.merchantName())
                .location(request.location())
                .status(TransactionStatus.PENDING)
                .user(user)
                .build();

        // Perform fraud detection
        FraudDetectionResult fraudResult = fraudDetectionService.detectFraud(transaction);
        transaction.setFraudScore(fraudResult.confidence());
        transaction.setIsFraud(fraudResult.isFraud());

        if (fraudResult.isFraud()) {
            transaction.setStatus(TransactionStatus.FRAUD_DETECTED);
            log.warn("Fraud detected for transaction: {}", transaction.getId());
        } else {
            transaction.setStatus(TransactionStatus.COMPLETED);
        }

        transaction = transactionRepository.save(transaction);
        log.info("Transaction created: {}", transaction.getId());

        return mapToResponse(transaction);
    }

    @Override
    public TransactionResponse getTransaction(Long id, String userEmail) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        return mapToResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> getAllTransactions(int page, int size) {
        return transactionRepository.findAll(PageRequest.of(page, size))
                .map(this::mapToResponse);
    }

    @Override
    public List<TransactionResponse> getUserTransactions(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return transactionRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse mapToResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getFraudScore(),
                transaction.getIsFraud(),
                transaction.getCreatedAt(),
                transaction.getMerchantName(),
                transaction.getLocation()
        );
    }
}
