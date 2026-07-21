
package com.finance.finance_tracker_api.mapper;

import com.finance.finance_tracker_api.dto.response.BudgetResponse;
import com.finance.finance_tracker_api.entity.Budget;

public class BudgetMapper {

    public static BudgetResponse toResponse(Budget budget) {

        return BudgetResponse.builder()
                .budgetId(budget.getBudgetId())
                .userId(budget.getUser().getUserId())
                .userName(budget.getUser().getFullName())
                .categoryId(budget.getCategory().getCategoryId())
                .categoryName(budget.getCategory().getCategoryName())
                .budgetAmount(budget.getBudgetAmount())
                .startDate(budget.getStartDate())
                .endDate(budget.getEndDate())
                .createdAt(budget.getCreatedAt())
                .build();
    }
}