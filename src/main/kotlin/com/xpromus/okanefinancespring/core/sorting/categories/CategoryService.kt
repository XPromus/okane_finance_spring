package com.xpromus.okanefinancespring.core.sorting.categories

import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.budgets.BudgetService
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.CategoryClassesDto
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.CreateCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.EditCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.GetCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.mapper.fromCreateCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.mapper.fromEditCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.mapper.toGetCategoryDto
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

    fun getAllCategories(id: UUID?, categoryName: String?): List<GetCategoryDto> {
        val categoriesToReturn = categoryRepository.findCategoriesByFields(id, categoryName)
        return categoriesToReturn.map { toGetCategoryDto(it) }
    }

    fun createCategory(createCategoryDto: CreateCategoryDto): GetCategoryDto {
        val categoryClasses = getCategoryClasses(
            parentCategoryID = createCategoryDto.parentCategoryID,
            childCategoryID = createCategoryDto.childCategoryID,
            targetBudgetID = createCategoryDto.targetBudgetID
        )
        val categoryToBeAdded = fromCreateCategoryDto(
            categoryClasses.parentCategory,
            categoryClasses.childCategory,
            categoryClasses.targetBudget,
            createCategoryDto
        )

        val savedCategory = categoryRepository.save(categoryToBeAdded)
        return toGetCategoryDto(savedCategory)
    }

    fun updateCategory(id: UUID, editCategoryDto: EditCategoryDto): GetCategoryDto {
        val categoryClasses = getCategoryClasses(
            parentCategoryID = editCategoryDto.parentCategoryID,
            childCategoryID = editCategoryDto.childCategoryID,
            targetBudgetID = editCategoryDto.targetBudgetID
        )

        return categoryRepository.findById(id).map {
            val save = categoryRepository.save(
                fromEditCategoryDto(
                    it,
                    categoryClasses.parentCategory,
                    categoryClasses.childCategory,
                    categoryClasses.targetBudget,
                    editCategoryDto
                )
            )
            toGetCategoryDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteCategory(id: UUID) {
        val toDeleteCategory = getCategoryById(id)
        categoryRepository.delete(toDeleteCategory)
    }

    fun getCategoryClasses(
        parentCategoryID: UUID?,
        childCategoryID: UUID?,
        targetBudgetID: UUID?,
    ): CategoryClassesDto {
        val parentCategory: Category? = if (parentCategoryID == null) {
            null
        } else {
            getCategoryById(parentCategoryID)
        }
        val childCategory: Category? = if (childCategoryID == null) {
            null
        } else {
            getCategoryById(childCategoryID)
        }
        val targetBudget: Budget? = if (targetBudgetID == null) {
            null
        } else {
            budgetService.getBudgetById(targetBudgetID)
        }

        return CategoryClassesDto(
            parentCategory = parentCategory,
            childCategory = childCategory,
            targetBudget = targetBudget
        )
    }
}