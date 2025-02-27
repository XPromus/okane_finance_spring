package com.xpromus.okanefinancespring.core.sorting.categories.mapper

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.sorting.categories.Category
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.CreateCategoryDto

fun fromCreateCategoryDto(
    parentCategory: Category?,
    childCategory: Category?,
    targetBudget: Budget?,
    createCategoryDto: CreateCategoryDto
): Category {
    return Category(
        categoryName = createCategoryDto.categoryName,
        parentCategory = parentCategory,
        childCategory = childCategory,
        targetBudget = targetBudget
    )
}
