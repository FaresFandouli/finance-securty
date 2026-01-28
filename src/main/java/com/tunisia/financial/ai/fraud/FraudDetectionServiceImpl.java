package com.tunisia.financial.ai.fraud;

import com.tunisia.financial.dto.response.FraudDetectionResult;
import com.tunisia.financial.dto.response.ModelResult;
import com.tunisia.financial.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final DJLFraudDetector djlDetector;
    private final ONNXFraudDetector onnxDetector;

    @Override
    public FraudDetectionResult detectFraud(Transaction transaction) {
        try {
            List<ModelResult> modelResults = new ArrayList<>();

            ModelResult djlResult = djlDetector.detect(transaction);
            modelResults.add(djlResult);

            ModelResult onnxResult = onnxDetector.detect(transaction);
            modelResults.add(onnxResult);

            double avgConfidence = modelResults.stream()
                    .mapToDouble(ModelResult::confidence)
                    .average()
                    .orElse(0.0);

            boolean isFraud = avgConfidence > 0.7;
            String reasoning = buildReasoning(modelResults, avgConfidence, isFraud);

            return new FraudDetectionResult(isFraud, avgConfidence, modelResults, reasoning);
        } catch (Exception e) {
            log.error("Error detecting fraud: {}", e.getMessage());
            return new FraudDetectionResult(false, 0.0, List.of(), "Detection unavailable");
        }
    }

    private String buildReasoning(List<ModelResult> results, double confidence, boolean isFraud) {
        if (isFraud) {
            return String.format("Fraud detected with %.2f%% confidence based on %d AI models", 
                    confidence * 100, results.size());
        } else {
            return String.format("Transaction appears legitimate with %.2f%% confidence", 
                    (1 - confidence) * 100);
        }
    }
}
