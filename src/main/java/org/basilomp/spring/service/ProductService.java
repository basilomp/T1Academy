package org.basilomp.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.spring.dao.ProductDao;
import org.basilomp.spring.dto.ProductRequestDto;
import org.basilomp.spring.dto.ProductResponseDto;
import org.basilomp.spring.exception.ProductNotFoundException;
import org.basilomp.spring.mapper.ProductMapper;
import org.basilomp.spring.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public ProductResponseDto getProductById(final Integer id) {
        final Product product = productDao.getProductById(id);
        final ProductMapper productMapper = new ProductMapper();
        if (product == null) {
            throw new ProductNotFoundException("Product with given id: " + id + " doesn't exist");
        }
        return productMapper.toDto(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productDao
                .getAllProducts()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public void saveProduct(ProductRequestDto productDto) {
        productDao.insertProduct(productDto);
    }

    public List<ProductResponseDto> getProductsByUserId(Integer userId) {
        List<Product> products = productDao.getProductByUserId(userId);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("There are no products with given user id: " + userId);
        }
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
