package com.tunisia.financial.ai.fraud;

import com.tunisia.financial.dto.response.ModelResult;
import com.tunisia.financial.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DJLFraudDetector {

    public ModelResult detect(Transaction transaction) {
        double[] features = extractFeatures(transaction);
        double confidence = calculateRiskScore(features);
        boolean prediction = confidence > 0.5;

        return new ModelResult("DJL-PyTorch", confidence, prediction);
    }

    private double[] extractFeatures(Transaction transaction) {
        return new double[]{
                transaction.getAmount().doubleValue(),
                transaction.getType().ordinal(),
                System.currentTimeMillis()
        };
    }

    private double calculateRiskScore(double[] features) {
        if (features[0] > 10000) return 0.8;
        if (features[0] > 5000) return 0.5;
        return 0.2;
    }
}
