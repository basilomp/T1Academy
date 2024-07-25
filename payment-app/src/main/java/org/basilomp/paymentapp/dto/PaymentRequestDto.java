package org.basilomp.paymentapp.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentRequestDto {
    public Integer productId;
    public BigDecimal paymentAmount;
}
