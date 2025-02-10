package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.CategoryClasses
import com.xpromus.okanefinancespring.dto.CategoryDto
import com.xpromus.okanefinancespring.entities.Budget
import com.xpromus.okanefinancespring.entities.Category

fun convertCategoryDtoToCategory(
    categoryDto: CategoryDto,
    categoryClasses: CategoryClasses
): Category {
    return Category(
        categoryName = categoryDto.categoryName,
        targetBudget = categoryClasses.targetBudget,
        parentCategory = categoryClasses.parentCategory,
        childCategory = categoryClasses.childCategory
    )
}
