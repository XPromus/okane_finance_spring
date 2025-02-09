package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.CategoryDto
import com.xpromus.okanefinancespring.entities.Budget
import com.xpromus.okanefinancespring.entities.Category

fun convertCategoryDtoToCategory(
    categoryDto: CategoryDto,
    targetBudget: Budget?,
    parentCategory: Category?,
    childCategory: Category?
): Category {
    return Category(
        categoryName = categoryDto.categoryName,
        targetBudget = targetBudget,
        parentCategory = parentCategory,
        childCategory = childCategory
    )
}
