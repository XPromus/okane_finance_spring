package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.services.CategoryService
import com.xpromus.okanefinancespring.entities.Category
import java.util.UUID

data class CategoryDto(
    val categoryName: String,
    val parentCategory: UUID?,
    val childCategory: UUID?,
    val targetBudget: UUID?
)

fun convertCategoryDtoToCategory(
    categoryService: CategoryService,
    categoryDto: CategoryDto
): Category {
    val parentCategory = categoryDto.parentCategory?.let { categoryService.getCategoryById(it) }
    val childCategory = categoryDto.childCategory?.let { categoryService.getCategoryById(it) }

    return Category(
        categoryName = categoryDto.categoryName,
        parentCategory = parentCategory,
        childCategory = childCategory
    )
}