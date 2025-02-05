package com.xpromus.okane_finance_spring.budget

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
