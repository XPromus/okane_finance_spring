package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.CategoryDto
import com.xpromus.okanefinancespring.entities.Category

fun convertCategoryDtoToCategory(
    categoryDto: CategoryDto
): Category {
    return Category(
        categoryName = categoryDto.categoryName
    )
}
