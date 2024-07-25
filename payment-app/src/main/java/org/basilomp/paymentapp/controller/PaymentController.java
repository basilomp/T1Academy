package org.basilomp.paymentapp.controller;

import lombok.RequiredArgsConstructor;
import org.basilomp.paymentapp.dto.PaymentRequestDto;
import org.basilomp.paymentapp.dto.PaymentResponseDto;
import org.basilomp.paymentapp.service.IntegrationProductService;
import org.basilomp.paymentapp.dto.ProductResponseDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final IntegrationProductService productService;

    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/user/products")
    public List<ProductResponseDto> getUserProducts(@RequestParam(name = "userId", required = true) Integer userId) {
        return productService.getProductsByUser(userId);
    }

    @PostMapping("/pay")
    public PaymentResponseDto executePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return productService.executePayment(paymentRequestDto);
    }
}
