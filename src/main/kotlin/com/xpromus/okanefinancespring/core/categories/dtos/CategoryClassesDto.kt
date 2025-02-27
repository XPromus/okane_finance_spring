package com.xpromus.okanefinancespring.core.categories.dtos

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.categories.Category

data class CategoryClassesDto(
    val parentCategory: Category?,
    val childCategory: Category?,
    val targetBudget: Budget?
)