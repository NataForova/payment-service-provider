package org.excample.paymentserviceprovider.model.paytech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetryStrategy {
    private int frequency;
    private String frequencyUnit;
    private int numberOfCycles;
    private int[] amountAdjustments;
}
