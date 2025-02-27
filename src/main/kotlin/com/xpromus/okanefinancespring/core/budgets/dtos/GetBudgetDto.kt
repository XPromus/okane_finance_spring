package com.xpromus.okanefinancespring.core.budgets.dtos

import java.util.*

data class GetBudgetDto(
    val id: UUID,
    val budgetName: String,
    val maxValue: Long,
    val targetCategoryIDs: List<UUID>
)
