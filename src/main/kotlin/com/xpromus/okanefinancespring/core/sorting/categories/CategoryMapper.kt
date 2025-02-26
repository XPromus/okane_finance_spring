package com.xpromus.okanefinancespring.core.sorting.categories

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
