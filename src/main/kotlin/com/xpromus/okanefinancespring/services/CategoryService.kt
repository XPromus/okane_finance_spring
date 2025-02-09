package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.CategoryDto
import com.xpromus.okanefinancespring.entities.Budget
import com.xpromus.okanefinancespring.entities.Category
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.mapper.convertCategoryDtoToCategory
import com.xpromus.okanefinancespring.repositories.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val budgetService: BudgetService,
) {

    fun getCategoryById(id: UUID): Category {
        return categoryRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Category with id $id could not be found")
        }
    }

    fun getAllCategories(id: UUID?, categoryName: String?): List<Category> {
        if ((id ?: categoryName) != null) {
            return categoryRepository.findCategoryByIdAndCategoryName(id, categoryName)
        }

        return categoryRepository.findAll()
    }

    fun createCategory(categoryDto: CategoryDto): Category {
        val targetBudget: Budget? = if (categoryDto.budgetId == null) {
            null
        } else {
            budgetService.getBudgetById(UUID.fromString(categoryDto.budgetId))
        }
        val parentCategory: Category? = if (categoryDto.parentCategoryId == null) {
            null
        } else {
            getCategoryById(UUID.fromString(categoryDto.parentCategoryId))
        }
        val childCategory: Category? = if (categoryDto.childCategoryId == null) {
            null
        } else {
            getCategoryById(UUID.fromString(categoryDto.childCategoryId))
        }

        return categoryRepository.save(
            convertCategoryDtoToCategory(
                categoryDto, targetBudget, parentCategory, childCategory
            )
        )
    }

    @Transactional
    fun deleteCategory(id: UUID) {
        val toDeleteCategory = getCategoryById(id)
        categoryRepository.delete(toDeleteCategory)
    }

    fun updateCategory(categoryDto: CategoryDto, id: UUID): Category {
        val targetBudget: Budget? = if (categoryDto.budgetId == null) {
            null
        } else {
            budgetService.getBudgetById(UUID.fromString(categoryDto.budgetId))
        }
        val parentCategory: Category? = if (categoryDto.parentCategoryId == null) {
            null
        } else {
            getCategoryById(UUID.fromString(categoryDto.parentCategoryId))
        }
        val childCategory: Category? = if (categoryDto.childCategoryId == null) {
            null
        } else {
            getCategoryById(UUID.fromString(categoryDto.childCategoryId))
        }

        return categoryRepository.findById(id).map {
            val save = categoryRepository.save(
                Category(
                    id = it.id,
                    categoryName = categoryDto.categoryName,
                    transactions = it.transactions,
                    targetBudget = targetBudget,
                    parentCategory = parentCategory,
                    childCategory = childCategory
                )
            )
            Category(
                id = save.id,
                categoryName = save.categoryName,
                transactions = save.transactions,
                targetBudget = save.targetBudget,
                parentCategory = save.parentCategory,
                childCategory = save.childCategory
            )
        }.orElseGet(null)
    }

}