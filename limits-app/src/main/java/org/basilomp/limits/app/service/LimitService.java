package org.basilomp.limits.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.limits.app.dto.ApprovedLimitUpdateRequest;
import org.basilomp.limits.app.dto.PaymentRequest;
import org.basilomp.limits.app.exceptions.UserNotFoundException;
import org.basilomp.limits.app.model.Limit;
import org.basilomp.limits.app.repository.LimitRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    public void executePayment(final PaymentRequest request) {
        if (request == null) {
            log.error("Error: payment request is null");
            return;
        }
        final Integer userId = request.getUserId();
        Limit limit = limitRepository.findFirstByUserId(userId).orElseThrow(() ->
                new UserNotFoundException("There is no limits for the provided userId found: " + userId));
        BigDecimal amount = request.getAmount();
        BigDecimal dailyLimit = limit.getDailyLimit();
        if (dailyLimit.compareTo(amount) < 0) {
            log.error("Insufficient funds. Cannot execute payment for user id {} and amount {}", userId, amount);
            return;
        }
        final Integer limitId = limit.getId();
        final BigDecimal updatedDailyLimit = dailyLimit.subtract(amount);
        limitRepository.updateDailyLimit(limitId, updatedDailyLimit);
    }

    @Scheduled(cron = "${application.scheduler.limits.reset-interval}")
    public void resetDailyLimits() {
        log.info("Resetting daily limits for all accounts");
        List<Limit> allLimits = limitRepository.findAll();
        if (allLimits.isEmpty()) {
            log.info("There is no records in the limits table");
            return;
        }
        limitRepository.resetDailyLimits();
    }

    public void changeApprovedDailyLimit(final ApprovedLimitUpdateRequest request) {
        log.info("Updating approved daily limit for an account");
        final Integer userId = request.getUserId();
        final BigDecimal approvedDailyLimit = request.getApprovedDailyLimit();
        limitRepository.updateApprovedDailyLimit(userId, approvedDailyLimit);
    }

}
