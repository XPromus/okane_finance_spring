package com.xpromus.okane_finance_spring.transaction.category

data class CategoryDto(
    val categoryName: String,
)

fun convertCategoryDtoToCategory(categoryDto: CategoryDto): Category {
    return Category(
        categoryName = categoryDto.categoryName
    )
}