package org.excample.paymentserviceprovider.model.paytech;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription {
    private String description;
    private BigDecimal amount;
    private String startTime;
    private Integer frequency;
    private String frequencyUnit;
    private Integer numberOfCycles;
    private RetryStrategy retryStrategy;

}
