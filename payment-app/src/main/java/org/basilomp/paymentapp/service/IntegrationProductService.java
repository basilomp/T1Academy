package org.basilomp.paymentapp.service;

import lombok.RequiredArgsConstructor;
import org.basilomp.paymentapp.dto.PaymentRequestDto;
import org.basilomp.paymentapp.dto.PaymentResponseDto;
import org.basilomp.paymentapp.dto.ProductResponseDto;
import org.basilomp.paymentapp.exception.InsufficientFundsException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IntegrationProductService {

    private final RestTemplate productRestClient;

    public List<ProductResponseDto> getProducts() {
        ParameterizedTypeReference<List<ProductResponseDto>> responseType = new ParameterizedTypeReference<List<ProductResponseDto>>() {};
        return productRestClient.exchange("/product/all", HttpMethod.GET, null, responseType).getBody();
    }

    public List<ProductResponseDto> getProductsByUser(Integer userId) {
        ParameterizedTypeReference<List<ProductResponseDto>> responseType = new ParameterizedTypeReference<>() {};
        Map<String, Integer> variables = new HashMap<>();
        variables.put("userId", userId);
        return productRestClient.exchange("/product/user?userId={userId}",
                HttpMethod.GET,
                null,
                responseType,
                variables).getBody();
    }

    public PaymentResponseDto executePayment(PaymentRequestDto paymentRequestDto) {
        HttpEntity<PaymentRequestDto> requestEntity = new HttpEntity<>(paymentRequestDto);
        try {
            return productRestClient.exchange("/product/pay",
                    HttpMethod.POST,
                    requestEntity,
                    PaymentResponseDto.class).getBody();
        } catch (HttpStatusCodeException e) {
            throw new InsufficientFundsException("Insufficient funds for payment request", HttpStatus.BAD_REQUEST);
        }

    }
}
