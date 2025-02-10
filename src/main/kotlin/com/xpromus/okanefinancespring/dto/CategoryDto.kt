package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Budget
import com.xpromus.okanefinancespring.entities.Category

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