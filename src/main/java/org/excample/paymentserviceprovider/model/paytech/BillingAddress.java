package org.excample.paymentserviceprovider.model.paytech;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingAddress {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String countryCode;
    private String postalCode;
    private String state;

}
