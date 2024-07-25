package org.basilomp.paymentapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Integer accountNumber;
    private BigDecimal balance;
    private String productType;
}
