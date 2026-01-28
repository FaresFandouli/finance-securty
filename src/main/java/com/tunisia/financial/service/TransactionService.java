package com.tunisia.financial.service;

import com.tunisia.financial.dto.request.TransactionRequest;
import com.tunisia.financial.dto.response.TransactionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request, String userEmail);
    TransactionResponse getTransaction(Long id, String userEmail);
    Page<TransactionResponse> getAllTransactions(int page, int size);
    List<TransactionResponse> getUserTransactions(String userEmail);
}
