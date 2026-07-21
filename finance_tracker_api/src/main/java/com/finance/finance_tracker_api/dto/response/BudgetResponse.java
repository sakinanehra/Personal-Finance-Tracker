package com.finance.finance_tracker_api.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetResponse {

    private Integer budgetId;

    private Integer userId;

    private String userName;

    private Integer categoryId;

    private String categoryName;

    private BigDecimal budgetAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime createdAt;
}