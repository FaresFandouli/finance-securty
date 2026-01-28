package com.tunisia.financial.ai.fraud;

import com.tunisia.financial.dto.response.ModelResult;
import com.tunisia.financial.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ONNXFraudDetector {

    public ModelResult detect(Transaction transaction) {
        double[] features = extractFeatures(transaction);
        double confidence = analyzeTransaction(features);
        boolean prediction = confidence > 0.5;

        return new ModelResult("ONNX-Runtime", confidence, prediction);
    }

    private double[] extractFeatures(Transaction transaction) {
        return new double[]{
                transaction.getAmount().doubleValue(),
                transaction.getType().ordinal()
        };
    }

    private double analyzeTransaction(double[] features) {
        if (features[0] > 15000) return 0.9;
        if (features[0] > 8000) return 0.6;
        return 0.3;
    }
}
