package com.xpromus.okanefinancespring.core.sorting.categories.dtos

import java.util.UUID

data class GetCategoryDto(
    val id: UUID,
    val categoryName: String,
    val transactionIDs: List<UUID>,
    val parentCategoryID: UUID?,
    val childCategoryID: UUID?,
    val targetBudgetID: UUID?
)
