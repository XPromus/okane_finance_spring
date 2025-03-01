package com.xpromus.okanefinancespring.core.categories.mapper

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.categories.Category
import com.xpromus.okanefinancespring.core.categories.dtos.EditCategoryDto

fun fromEditCategoryDto(
    category: Category,
    parentCategory: Category?,
    childCategory: Category?,
    targetBudget: Budget?,
    editCategoryDto: EditCategoryDto
): Category {
    return Category(
        id = category.id,
        categoryName = editCategoryDto.categoryName ?: category.categoryName,
        transactions = category.transactions,
        parentCategory = parentCategory,
        childCategory = childCategory,
        targetBudget = targetBudget
    )
}
