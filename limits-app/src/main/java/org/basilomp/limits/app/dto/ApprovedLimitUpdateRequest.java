package org.basilomp.limits.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ApprovedLimitUpdateRequest {

    public Integer userId;

    public BigDecimal approvedDailyLimit;
}
