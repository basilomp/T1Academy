package org.basilomp.productapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentResponseDto {

    public Integer productId;
    public BigDecimal amount;
}
