package org.excample.paymentserviceprovider.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    private String referenceId;
    private String citizenshipCountryCode;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String locale;
    private String accountNumber;
    private String accountName;
    private String bank;
    private String bankBranch;
    private String documentType;
    private String documentNumber;
    private String routingGroup;
    private Boolean kycStatus;
    private Boolean paymentInstrumentKycStatus;
    private String dateOfFirstDeposit;
    private BigDecimal depositsAmount;
    private BigDecimal withdrawalsAmount;
    private Integer depositsCnt;
    private Integer withdrawalsCnt;

}
