package com.xpromus.okanefinancespring.budgets

fun convertBudgetDtoToBudget(
    budgetDto: BudgetDto
): Budget {
    return Budget(
        maxValue = budgetDto.maxValue
    )
}
