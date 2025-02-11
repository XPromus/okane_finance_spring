package com.xpromus.okanefinancespring.sorting.categories

import com.xpromus.okanefinancespring.budgets.Budget
import com.xpromus.okanefinancespring.budgets.BudgetService
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
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
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Category", id.toString())
            )
        }
    }

    fun getAllCategories(id: UUID?, categoryName: String?): List<Category> {
        return categoryRepository.findCategoriesByFields(id, categoryName)
    }

    fun createCategory(categoryDto: CategoryDto): Category {
        val categoryClasses = getCategoryClasses(
            parentCategoryId = categoryDto.parentCategoryId,
            childCategoryId = categoryDto.childCategoryId,
            targetBudgetId = categoryDto.budgetId
        )

        val categoryToBeAdded = convertCategoryDtoToCategory(
            categoryDto, categoryClasses
        )

        return categoryRepository.save(categoryToBeAdded)
    }

    @Transactional
    fun deleteCategory(id: UUID) {
        val toDeleteCategory = getCategoryById(id)
        categoryRepository.delete(toDeleteCategory)
    }

    fun updateCategory(categoryDto: CategoryDto, id: UUID): Category {
        val categoryClasses = getCategoryClasses(
            parentCategoryId = categoryDto.parentCategoryId,
            childCategoryId = categoryDto.childCategoryId,
            targetBudgetId = categoryDto.budgetId
        )

        val updatedCategory = convertCategoryDtoToCategory(
            categoryDto, categoryClasses
        )

        return categoryRepository.findById(id).map {
            val save = categoryRepository.save(
                Category(
                    id = it.id,
                    categoryName = updatedCategory.categoryName,
                    transactions = updatedCategory.transactions,
                    targetBudget = updatedCategory.targetBudget,
                    parentCategory = updatedCategory.parentCategory,
                    childCategory = updatedCategory.childCategory
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

    fun getCategoryClasses(
        parentCategoryId: String?,
        childCategoryId: String?,
        targetBudgetId: String?,
    ): CategoryClasses {
        val parentCategory: Category? = if (parentCategoryId == null) {
            null
        } else {
            getCategoryById(UUID.fromString(parentCategoryId))
        }
        val childCategory: Category? = if (childCategoryId == null) {
            null
        } else {
            getCategoryById(UUID.fromString(childCategoryId))
        }
        val targetBudget: Budget? = if (targetBudgetId == null) {
            null
        } else {
            budgetService.getBudgetById(UUID.fromString(targetBudgetId))
        }

        return CategoryClasses(
            parentCategory = parentCategory,
            childCategory = childCategory,
            targetBudget = targetBudget
        )
    }

}