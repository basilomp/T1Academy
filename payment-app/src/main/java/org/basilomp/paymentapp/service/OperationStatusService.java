package org.basilomp.paymentapp.service;

import lombok.RequiredArgsConstructor;
import org.basilomp.paymentapp.entity.OperationStatus;
import org.basilomp.paymentapp.exception.OperationStatusNotFoundException;
import org.basilomp.paymentapp.repository.OperationStatusRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationStatusService {

    private final OperationStatusRepository operationStatusRepository;

    OperationStatus findStatusByCode(Integer code) {
        return operationStatusRepository
                .findOperationStatusByCode(code)
                .orElseThrow(() -> new OperationStatusNotFoundException("Status not found"));
    }
}
