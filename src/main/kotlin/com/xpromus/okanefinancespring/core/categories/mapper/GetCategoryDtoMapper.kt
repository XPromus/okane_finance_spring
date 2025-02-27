package com.xpromus.okanefinancespring.core.categories.mapper

import com.xpromus.okanefinancespring.core.categories.Category
import com.xpromus.okanefinancespring.core.categories.dtos.GetCategoryDto
import java.util.UUID

fun toGetCategoryDto(category: Category): GetCategoryDto {
    val parentCategoryID: UUID? = if (category.parentCategory == null) {
        null
    } else {
        category.parentCategory!!.id!!
    }

    val childCategoryID: UUID? = if (category.childCategory == null) {
        null
    } else {
        category.childCategory!!.id!!
    }

    val targetBudgetID: UUID? = if (category.targetBudget == null) {
        null
    } else {
        category.targetBudget!!.id!!
    }

    return GetCategoryDto(
        id = category.id!!,
        categoryName = category.categoryName,
        transactionIDs = category.transactions.map { it.id!! },
        parentCategoryID = parentCategoryID,
        childCategoryID = childCategoryID,
        targetBudgetID = targetBudgetID
    )
}
