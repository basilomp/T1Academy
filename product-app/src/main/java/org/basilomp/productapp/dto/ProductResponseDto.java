package org.basilomp.productapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Integer accountNumber;
    private BigDecimal balance;
    private String productType;
}
