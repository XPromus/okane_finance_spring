package com.xpromus.okanefinancespring.core.categories.dtos

import java.util.UUID

data class CreateCategoryDto(
    val categoryName: String,
    val parentCategoryID: UUID?,
    val childCategoryID: UUID?,
    val targetBudgetID: UUID?
)
