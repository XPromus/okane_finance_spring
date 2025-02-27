package com.xpromus.okanefinancespring.core.budgets.mapper

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.budgets.dtos.EditBudgetDto

fun fromEditBudgetDto(budget: Budget, editBudgetDto: EditBudgetDto): Budget {
    return Budget(
        id = budget.id,
        budgetName = editBudgetDto.budgetName ?: budget.budgetName,
        maxValue = editBudgetDto.maxValue ?: budget.maxValue,
        targetCategories = budget.targetCategories
    )
}