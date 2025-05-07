package org.excample.paymentserviceprovider.model.paytech;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.excample.paymentserviceprovider.model.Customer;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest {
    private String referenceId;
    private String paymentType;
    private String paymentMethod;
    private BigDecimal amount;
    private String currency;
    private String parentPaymentId;
    private String description;
    private Card card;
    private Customer customer;
    private BillingAddress billingAddress;
    private String returnUrl;
    private String webhookUrl;
    private Boolean startRecurring;
    private Boolean preAuth;
    private String recurringToken;
    private Subscription subscription;
    private AdditionalParameters additionalParameters;
}
