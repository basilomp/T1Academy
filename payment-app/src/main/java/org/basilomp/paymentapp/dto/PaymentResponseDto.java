package org.basilomp.paymentapp.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentResponseDto {

    public Integer productId;
    public BigDecimal amount;
}

