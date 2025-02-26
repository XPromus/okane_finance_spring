package com.xpromus.okanefinancespring.core.budgets

fun convertBudgetDtoToBudget(
    budgetDto: BudgetDto
): Budget {
    return Budget(
        maxValue = budgetDto.maxValue
    )
}
