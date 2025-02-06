package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.BudgetDto
import com.xpromus.okanefinancespring.entities.Budget

fun convertBudgetDtoToBudget(budgetDto: BudgetDto): Budget {
    return Budget(
        budgetName = budgetDto.budgetName,
        maxValue = budgetDto.maxValue
    )
}
