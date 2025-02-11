package com.xpromus.okanefinancespring.sorting.categories

import com.xpromus.okanefinancespring.budgets.Budget

data class CategoryDto(
    val categoryName: String,
    val budgetId: String?,
    val parentCategoryId: String?,
    val childCategoryId: String?
)

data class CategoryClasses(
    val parentCategory: Category?,
    val childCategory: Category?,
    val targetBudget: Budget?
)