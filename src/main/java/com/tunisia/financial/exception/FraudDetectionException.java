package com.tunisia.financial.exception;

public class FraudDetectionException extends BusinessException {
    public FraudDetectionException(String message) {
        super(message);
    }

    public FraudDetectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
