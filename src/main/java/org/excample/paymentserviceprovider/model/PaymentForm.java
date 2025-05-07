package org.excample.paymentserviceprovider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm {
    private BigDecimal amount;
    private String customerReferenceId;
    private String currency;
    private String paymentType;
}
