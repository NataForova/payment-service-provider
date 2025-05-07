package org.excample.paymentserviceprovider.model.paytech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDetails {
    private String customerAccountNumber;
    private String cardToken;
    private String cardholderName;
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String cardIssuingCountryCode;
}
