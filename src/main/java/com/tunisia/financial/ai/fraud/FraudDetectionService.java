package com.tunisia.financial.ai.fraud;

import com.tunisia.financial.dto.response.FraudDetectionResult;
import com.tunisia.financial.entity.Transaction;

public interface FraudDetectionService {
    FraudDetectionResult detectFraud(Transaction transaction);
}
