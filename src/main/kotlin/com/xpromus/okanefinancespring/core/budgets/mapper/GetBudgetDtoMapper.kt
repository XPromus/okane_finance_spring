package com.xpromus.okanefinancespring.core.budgets.mapper

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.budgets.dtos.GetBudgetDto

fun toGetBudgetDto(budget: Budget): GetBudgetDto {
    return GetBudgetDto(
        id = budget.id!!,
        budgetName = budget.budgetName,
        maxValue = budget.maxValue,
        targetCategoryIDs = budget.targetCategories.map { it.id!! }
    )
}