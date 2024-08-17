package org.basilomp.limits.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.limits.app.dto.ApprovedLimitUpdateRequest;
import org.basilomp.limits.app.dto.PaymentRequest;
import org.basilomp.limits.app.service.LimitService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/limits")
public class LimitController {

    private final LimitService limitService;

    @PutMapping
    public void executePayment(@RequestBody final PaymentRequest request) {
        final Integer userId = request.getUserId();
        final BigDecimal amount = request.getAmount();
        log.info("Accepted payment request for userId {} and amount {}", userId, amount);
        limitService.executePayment(request);
    }

    @PutMapping("/update")
    public void updateApprovedLimit(@RequestBody final ApprovedLimitUpdateRequest request) {
        final Integer userId = request.getUserId();
        final BigDecimal approvedDailyLimit = request.getApprovedDailyLimit();
        log.info("Accepted request for updating daily limit for account with id {} to {}",
                userId, approvedDailyLimit);
        limitService.changeApprovedDailyLimit(request);
    }
}
