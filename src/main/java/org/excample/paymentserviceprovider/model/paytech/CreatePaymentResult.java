package org.excample.paymentserviceprovider.model.paytech;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.excample.paymentserviceprovider.model.Customer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResult {
    private String id;
    private String referenceId;
    private String created;
    private String paymentType;
    private String state;
    private String description;
    private String parentPaymentId;
    private String paymentMethod;
    private PaymentMethodDetails paymentMethodDetails;
    private double amount;
    private String currency;
    private double customerAmount;
    private String customerCurrency;
    private String redirectUrl;
    private String errorCode;
    private String errorMessage;
    private String externalResultCode;
    private Customer customer;
    private BillingAddress billingAddress;
    private boolean startRecurring;
    private boolean preAuth;
    private String recurringToken;
    private String terminalName;
    private double externalFeeAmount;
    private String externalFeeCurrency;
}
