package org.excample.paymentserviceprovider.exception;

public class InvalidRequestFieldException extends RuntimeException {
    public InvalidRequestFieldException(String message) {
        super(message);
    }
}
