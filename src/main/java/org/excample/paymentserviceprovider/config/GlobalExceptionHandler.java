package org.excample.paymentserviceprovider.config;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.excample.paymentserviceprovider.exception.InvalidRequestFieldException;
import org.excample.paymentserviceprovider.exception.InvalidResponseException;
import org.excample.paymentserviceprovider.model.PaymentForm;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidRequestFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidRequestFieldException(InvalidRequestFieldException e, Model model) {
        String errorMessage;
        try {
            errorMessage = e.getMessage();
        } catch (Exception ex) {
            errorMessage = "An unknown error has occurred.";
        }
        log.error("Error during request {}", errorMessage);
        model.addAttribute("payment", new PaymentForm());
        model.addAttribute("amountErrorMessage", errorMessage);
        return "payment";
    }
    @ExceptionHandler(FeignException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFeignStatusException(FeignException e, Model model) {
        String errorMessage;
        try {
            errorMessage = e.contentUTF8();
        } catch (Exception ex) {
            errorMessage = "An unknown error has occurred.";
        }
        log.error("Error during request {}", errorMessage);

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @ExceptionHandler(FeignException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleFeignUnauthorizedException(FeignException e, Model model) {
        String errorMessage = e.getLocalizedMessage();
        log.error("Error during request {}", errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @ExceptionHandler(InvalidResponseException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handleFeignInvalidResponseException(InvalidResponseException e, Model model) {
        String errorMessage = e.getLocalizedMessage();
        log.error("Error during request {}", errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
