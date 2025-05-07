package org.excample.paymentserviceprovider.controller;

import org.excample.paymentserviceprovider.model.PaymentForm;
import org.excample.paymentserviceprovider.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PaymentsController {
    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping()
    public String showPaymentPage(Model model) {
        model.addAttribute("payment", new PaymentForm());
        return "payment";
    }

    @PostMapping("/create")
    public String createPayment(@ModelAttribute PaymentForm request, Model model) {

            model.addAttribute("amount", request.getAmount());
            var redirectUrl = paymentService.createPayment(request);
            return String.format("redirect:%s", redirectUrl);

    }
}
