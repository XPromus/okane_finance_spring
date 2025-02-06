package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Budget

data class BudgetDto(
    val budgetName: String,
    val maxValue: Long
)

fun convertBudgetDtoToBudget(budgetDto: BudgetDto): Budget {
    return Budget(
        budgetName = budgetDto.budgetName,
        maxValue = budgetDto.maxValue
    )
}
