package org.basilomp.productapp.service;

import org.basilomp.productapp.dto.ExecutorResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExecutorPaymentService {

    private final RestTemplate executorRestClient;

    public ExecutorPaymentService(RestTemplate executorRestClient) {
        this.executorRestClient = executorRestClient;
    }

    public ExecutorResponseDto executePaymentOk() {
        return executorRestClient.postForObject("/payments/execute", null, ExecutorResponseDto.class);
    }

    public ExecutorResponseDto executePaymentFailed() {
        return executorRestClient.postForObject("/payments/execute/400", null, ExecutorResponseDto.class);
    }
}
