package com.xpromus.okane_finance_spring.category

data class CategoryDto(
    val categoryName: String,
)

fun convertCategoryDtoToCategory(categoryDto: CategoryDto): Category {
    return Category(
        categoryName = categoryDto.categoryName
    )
}