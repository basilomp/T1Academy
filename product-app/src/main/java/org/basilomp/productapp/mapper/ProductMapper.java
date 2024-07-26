package org.basilomp.productapp.mapper;

import org.basilomp.productapp.dto.PaymentResponseDto;
import org.basilomp.productapp.dto.ProductRequestDto;
import org.basilomp.productapp.dto.ProductResponseDto;
import org.basilomp.productapp.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toEntity(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setAccountNumber(requestDto.getAccountNumber());
        product.setBalance(requestDto.getBalance());
        product.setProductType(requestDto.getProductType());
        return product;
    }

    public static ProductResponseDto toDto(Product entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setAccountNumber(entity.getAccountNumber());
        responseDto.setBalance(entity.getBalance());
        responseDto.setProductType(entity.getProductType());
        return responseDto;
    }

    public static PaymentResponseDto toPaymentResponseDto(Product entity) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setProductId(entity.getId());
        paymentResponseDto.setAmount(entity.getBalance());
        return paymentResponseDto;
    }
}
