package org.basilomp.spring.mapper;

import org.basilomp.spring.dto.ProductRequestDto;
import org.basilomp.spring.dto.ProductResponseDto;
import org.basilomp.spring.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setAccountNumber(requestDto.getAccountNumber());
        product.setBalance(requestDto.getBalance());
        product.setProductType(requestDto.getProductType());
        product.setUserId(requestDto.getUserId());
        return product;
    }

    public static ProductResponseDto toDto(Product entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setAccountNumber(entity.getAccountNumber());
        responseDto.setBalance(entity.getBalance());
        responseDto.setProductType(entity.getProductType());
        return responseDto;
    }
}
