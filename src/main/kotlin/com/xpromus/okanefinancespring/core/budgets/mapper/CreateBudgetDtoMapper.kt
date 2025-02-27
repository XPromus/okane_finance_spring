package com.xpromus.okanefinancespring.core.budgets.mapper

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.budgets.dtos.CreateBudgetDto

fun fromCreateBudgetDto(createBudgetDto: CreateBudgetDto): Budget {
    return Budget(
        budgetName = createBudgetDto.budgetName,
        maxValue = createBudgetDto.maxValue
    )
}
