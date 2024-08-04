package org.basilomp.paymentapp.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationStatusDictionary {
    SUCCESS(20),
    INSUFFICIENT_BALANCE(40),
    EXTERNAL_SERVICE_UNAVAILABLE(50);

    private Integer code;


}
