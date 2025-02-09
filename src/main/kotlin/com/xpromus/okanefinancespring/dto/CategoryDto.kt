package com.xpromus.okanefinancespring.dto

data class CategoryDto(
    val categoryName: String,
    val budgetId: String?,
    val parentCategoryId: String?,
    val childCategoryId: String?
)
