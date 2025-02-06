package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.CategoryDto
import com.xpromus.okanefinancespring.entities.Category
import com.xpromus.okanefinancespring.services.CategoryService

fun convertCategoryDtoToCategory(
    categoryDto: CategoryDto
): Category {
    return Category(
        categoryName = categoryDto.categoryName
    )
}
