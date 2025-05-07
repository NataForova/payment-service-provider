package org.excample.paymentserviceprovider.service;

import lombok.extern.slf4j.Slf4j;
import org.excample.paymentserviceprovider.exception.InvalidRequestFieldException;
import org.excample.paymentserviceprovider.exception.InvalidResponseException;
import org.excample.paymentserviceprovider.model.Customer;
import org.excample.paymentserviceprovider.model.PaymentForm;
import org.excample.paymentserviceprovider.model.paytech.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class PaymentService {
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.00001");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("999999.99");
    private static final int MAX_CUSTOMER_ID_LENGTH = 128;

    private static final String DEFAULT_PAYMENT_METHOD = "DEPOSIT";
    private static final String DEFAULT_CURRENCY = "EUR";

    private final PayTechClient payTechClient;

    @Autowired
    public PaymentService(PayTechClient payTechClient) {
        this.payTechClient = payTechClient;
    }

    public String createPayment(PaymentForm request) {
        log.info("Starting to create payment for test-customer");
        var amount = request.getAmount();
        validateAmount(amount);
        validateCustomerId(request.getCustomerReferenceId());

        var requestBody = PaymentRequest.builder()
                .currency(getStringValueOrDefault(request.getCurrency(), DEFAULT_CURRENCY))
                .paymentType(getStringValueOrDefault(request.getPaymentType(), DEFAULT_PAYMENT_METHOD))
                .amount(amount)
                .customer(Customer.builder().referenceId(request.getCustomerReferenceId()).build())
                .build();

        var response = payTechClient.createPayment(requestBody);
        log.info("Finished to create payment for test-customer");
        if (response == null || response.getResult() == null) {
            throw new InvalidResponseException("Paytech api response doesn't contain redirect link");
        }
        return response.getResult().getRedirectUrl();
    }

    public static void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidRequestFieldException("Amount can not be null");
        }
        if (amount.compareTo(MIN_AMOUNT) < 0) {
            throw new InvalidRequestFieldException("Amount should be grater than 1e-18");
        }
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new InvalidRequestFieldException("Amount should be less than 999999.99.");
        }
    }

    public static void validateCustomerId(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            throw new InvalidRequestFieldException("Customer can not be null");
        }
        if (customerId.length() > MAX_CUSTOMER_ID_LENGTH) {
            throw new InvalidRequestFieldException("Customer id is too long");
        }
    }

    private String getStringValueOrDefault(Object attribute, String defaultValue) {
        return Optional.ofNullable(attribute)
                .map(Object::toString)
                .orElse(defaultValue);
    }
}
