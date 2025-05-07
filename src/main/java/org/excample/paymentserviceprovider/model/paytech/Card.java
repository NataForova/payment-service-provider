package org.excample.paymentserviceprovider.model.paytech;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {
    private String cardNumber;
    private String cardToken;
    private String cardholderName;
    private String cardSecurityCode;
    private String expiryMonth;
    private String expiryYear;
}
