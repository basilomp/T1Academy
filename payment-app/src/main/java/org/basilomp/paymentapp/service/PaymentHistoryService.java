package org.basilomp.paymentapp.service;

import lombok.RequiredArgsConstructor;
import org.basilomp.paymentapp.client.ProductIntegrationClient;
import org.basilomp.paymentapp.dto.PaymentRequestDto;
import org.basilomp.paymentapp.dto.PaymentResponseDto;
import org.basilomp.paymentapp.entity.OperationStatus;
import org.basilomp.paymentapp.entity.PaymentHistory;
import org.basilomp.paymentapp.enumerated.OperationStatusDictionary;
import org.basilomp.paymentapp.repository.PaymentHistoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final OperationStatusService operationStatusService;
    private final ProductIntegrationClient productIntegrationClient;

    public PaymentResponseDto executePayment(PaymentRequestDto request) {
        PaymentResponseDto response = productIntegrationClient.executePayment(request);
        Integer successCode = OperationStatusDictionary.SUCCESS.getCode();
        OperationStatus successStatus = operationStatusService.findStatusByCode(successCode);
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setProductId(response.getProductId());
        paymentHistory.setAmount(response.getAmount());
        paymentHistory.setStatusCode(successStatus);
        paymentHistoryRepository.save(paymentHistory);
        return response;
    }
}
