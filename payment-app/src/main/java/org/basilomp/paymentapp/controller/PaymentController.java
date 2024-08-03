package org.basilomp.paymentapp.controller;

import lombok.RequiredArgsConstructor;
import org.basilomp.paymentapp.client.ProductIntegrationClient;
import org.basilomp.paymentapp.dto.PaymentRequestDto;
import org.basilomp.paymentapp.dto.PaymentResponseDto;
import org.basilomp.paymentapp.dto.ProductResponseDto;
import org.basilomp.paymentapp.service.PaymentHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentHistoryService historyService;
    private final ProductIntegrationClient integrationClient;

    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() {
        return integrationClient.getProducts();
    }

    @GetMapping("/user/products")
    public List<ProductResponseDto> getUserProducts(@RequestParam(name = "userId", required = true) Integer userId) {
        return integrationClient.getProductsByUser(userId);
    }

    @PostMapping("/pay")
    public PaymentResponseDto executePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return historyService.executePayment(paymentRequestDto);
    }
}
