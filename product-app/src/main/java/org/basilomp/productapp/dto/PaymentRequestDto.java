package org.basilomp.productapp.dto;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class PaymentRequestDto {
    public Integer productId;
    public BigDecimal paymentAmount;
}
