package org.excample.paymentserviceprovider.service;

import org.excample.paymentserviceprovider.config.PayTechRequestInterceptor;
import org.excample.paymentserviceprovider.model.CreatePaymentResponse;
import org.excample.paymentserviceprovider.model.paytech.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name ="payTechClient", path = "api/v1",  url = "${payment-service-provider.pay-tech.api-url}", configuration = PayTechRequestInterceptor.class)
public interface PayTechClient {

    @PostMapping(value = "payments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CreatePaymentResponse createPayment(@RequestBody PaymentRequest request);

}