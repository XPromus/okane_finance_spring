package com.xpromus.okanefinancespring.core.sorting.categories.dtos

import java.util.UUID

data class EditCategoryDto(
    val categoryName: String?,
    val parentCategoryID: UUID?,
    val childCategoryID: UUID?,
    val targetBudgetID: UUID?
)
