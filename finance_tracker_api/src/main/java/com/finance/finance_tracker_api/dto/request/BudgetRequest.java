package com.finance.finance_tracker_api.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer categoryId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal budgetAmount;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}