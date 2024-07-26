package org.basilomp.productapp.controller;

import lombok.RequiredArgsConstructor;
import org.basilomp.productapp.dto.ExecutorResponseDto;
import org.basilomp.productapp.service.ExecutorPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final ExecutorPaymentService executorPaymentService;

    @GetMapping("/healthCheck")
    public ExecutorResponseDto connectionHealthCheck() {
        return executorPaymentService.executePaymentOk();
    }

    @GetMapping("/errorCheck")
    public ExecutorResponseDto errorCheck() {
        return executorPaymentService.executePaymentFailed();
    }
}
