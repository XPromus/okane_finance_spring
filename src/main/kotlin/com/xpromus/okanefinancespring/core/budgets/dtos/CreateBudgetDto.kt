package com.xpromus.okanefinancespring.core.budgets.dtos

data class CreateBudgetDto(
    val budgetName: String,
    val maxValue: Long
)
