package org.basilomp.spring.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    private Integer id;
    private Integer accountNumber;
    private BigDecimal balance;
    private String productType;
    private Integer userId;
}
