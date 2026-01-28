package com.tunisia.financial.ai.risk;

import com.tunisia.financial.dto.response.RiskAssessment;
import com.tunisia.financial.entity.User;
import com.tunisia.financial.enums.RiskCategory;
import com.tunisia.financial.exception.EntityNotFoundException;
import com.tunisia.financial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditRiskServiceImpl implements CreditRiskService {

    private final UserRepository userRepository;

    @Override
    public RiskAssessment assessRisk(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        double riskScore = calculateRiskScore(user);
        RiskCategory category = RiskCategory.fromScore(riskScore);

        List<String> riskFactors = identifyRiskFactors(user, riskScore);
        List<String> recommendations = generateRecommendations(category);

        String analysis = String.format(
                "Risk assessment for %s: Score %.2f (%s). Based on transaction history.",
                user.getUsername(), riskScore, category.getDescription()
        );

        return new RiskAssessment(riskScore, category, analysis, riskFactors, recommendations);
    }

    private double calculateRiskScore(User user) {
        int transactionCount = user.getTransactions().size();
        if (transactionCount < 5) return 0.7;
        if (transactionCount < 20) return 0.4;
        return 0.2;
    }

    private List<String> identifyRiskFactors(User user, double score) {
        if (score > 0.6) {
            return Arrays.asList("Limited transaction history", "New account");
        }
        return Arrays.asList("No significant risk factors identified");
    }

    private List<String> generateRecommendations(RiskCategory category) {
        return switch (category) {
            case CRITICAL, HIGH -> Arrays.asList("Require additional verification", "Limit transaction amounts");
            case MEDIUM -> Arrays.asList("Standard monitoring procedures");
            case LOW -> Arrays.asList("Continue normal operations");
        };
    }
}
