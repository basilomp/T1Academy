package org.basilomp.productapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.productapp.dto.PaymentRequestDto;
import org.basilomp.productapp.dto.PaymentResponseDto;
import org.basilomp.productapp.entity.User;
import org.basilomp.productapp.dto.ProductRequestDto;
import org.basilomp.productapp.dto.ProductResponseDto;
import org.basilomp.productapp.entity.Product;
import org.basilomp.productapp.exception.InsufficientFundsException;
import org.basilomp.productapp.exception.ProductNotFoundException;
import org.basilomp.productapp.mapper.ProductMapper;
import org.basilomp.productapp.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductResponseDto getProductById(final Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with given id: " + id + " doesn't exist"));
        return ProductMapper.toDto(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public void saveProduct(ProductRequestDto productDto) {
        final User user = userService.getUserById(productDto.getUserId());
        Product product = ProductMapper.toEntity(productDto);
        product.setUser(user);
        System.out.println("New Product: " + product);
        productRepository.save(product);
    }

    public List<ProductResponseDto> getProductsByUserId(Integer userId) {
        return productRepository.findAllByUserId(userId)
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public PaymentResponseDto executePayment(final PaymentRequestDto paymentRequestDto) {
        log.info("Payment request: " + paymentRequestDto);
        final Integer productId = paymentRequestDto.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " doesn't exist"));
        final BigDecimal paymentAmount = paymentRequestDto.getPaymentAmount();
        final BigDecimal productBalance = product.getBalance();
        if (productBalance.compareTo(paymentAmount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for payment", HttpStatus.BAD_REQUEST);
        }
        product.setBalance(productBalance.subtract(paymentAmount));
        log.info(product.toString());
        productRepository.save(product);
        return ProductMapper.toPaymentResponseDto(product);
    }
}
