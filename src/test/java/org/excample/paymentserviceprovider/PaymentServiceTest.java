package org.excample.paymentserviceprovider;

import org.excample.paymentserviceprovider.exception.InvalidRequestFieldException;
import org.excample.paymentserviceprovider.model.CreatePaymentResponse;
import org.excample.paymentserviceprovider.model.PaymentForm;
import org.excample.paymentserviceprovider.model.paytech.CreatePaymentResult;
import org.excample.paymentserviceprovider.model.paytech.PaymentRequest;
import org.excample.paymentserviceprovider.service.PayTechClient;
import org.excample.paymentserviceprovider.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PayTechClient payTechClient;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePayment_Positive() {
        PaymentForm request = new PaymentForm();
        request.setAmount(new BigDecimal("500.00"));
        request.setCurrency("USD");
        request.setCustomerReferenceId("test_customer");
        request.setPaymentType("WITHDRAW");

        CreatePaymentResponse mockResponse = CreatePaymentResponse.builder()
                .result(CreatePaymentResult.builder()
                        .redirectUrl("http://redirect.url")
                        .build())
                .build();
        when(payTechClient.createPayment(any(PaymentRequest.class))).thenReturn(mockResponse);

        String redirectUrl = paymentService.createPayment(request);

        assertEquals("http://redirect.url", redirectUrl);
        verify(payTechClient, times(1)).createPayment(any(PaymentRequest.class));
    }

    @Test
    void testCreatePayment_NullAmount() {
        PaymentForm request = new PaymentForm();

        InvalidRequestFieldException exception = assertThrows(InvalidRequestFieldException.class, () -> {
            paymentService.createPayment(request);
        });

        assertEquals("Amount can not be null", exception.getMessage());
    }

    @Test
    void testCreatePayment_TooSmallAmount() {
        PaymentForm request = new PaymentForm();
        request.setAmount(new BigDecimal("1e-19"));

        InvalidRequestFieldException exception = assertThrows(InvalidRequestFieldException.class, () -> {
            paymentService.createPayment(request);
        });

        assertEquals("Amount should be grater than 1e-18", exception.getMessage());
    }

    @Test
    void testCreatePayment_TooLargeAmount() {
        PaymentForm request = new PaymentForm();
        request.setAmount(new BigDecimal("1000000"));

        InvalidRequestFieldException exception = assertThrows(InvalidRequestFieldException.class, () -> {
            paymentService.createPayment(request);
        });

        assertEquals("Amount should be less than 999999.99.", exception.getMessage());
    }

    @Test
    void testCreatePayment_NullCustomerId() {
        PaymentForm request = new PaymentForm();
        request.setAmount(new BigDecimal("500.00"));


        InvalidRequestFieldException exception = assertThrows(InvalidRequestFieldException.class, () -> {
            paymentService.createPayment(request);
        });

        assertEquals("Customer can not be null", exception.getMessage());
    }

    @Test
    void testCreatePayment_EmptyCustomerId() {
        PaymentForm request = new PaymentForm();
        request.setAmount(new BigDecimal("500.00"));
        request.setCustomerReferenceId("");


        InvalidRequestFieldException exception = assertThrows(InvalidRequestFieldException.class, () -> {
            paymentService.createPayment(request);
        });

        assertEquals("Customer can not be null", exception.getMessage());
    }


    @Test
    void testCreatePayment_TooLongCustomerId() {
        PaymentForm request = new PaymentForm();
        request.setAmount(new BigDecimal("500.00"));
        request.setCustomerReferenceId(generateRandomString(129));


        InvalidRequestFieldException exception = assertThrows(InvalidRequestFieldException.class, () -> {
            paymentService.createPayment(request);
        });

        assertEquals("Customer id is too long", exception.getMessage());
    }

    public static String generateRandomString(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        return random.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}