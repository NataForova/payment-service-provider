package org.excample.paymentserviceprovider.model;

import lombok.*;
import org.excample.paymentserviceprovider.model.paytech.CreatePaymentResult;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse extends BaseResponse {
    private CreatePaymentResult result;
}
