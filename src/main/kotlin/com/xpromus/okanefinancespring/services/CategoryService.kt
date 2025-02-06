package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.CategoryDto
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
    private val transactionService: TransactionService,
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
        return categoryRepository.save(convertCategoryDtoToCategory(categoryDto))
    }

    @Transactional
    fun deleteCategory(id: UUID) {
        val toDeleteCategory = getCategoryById(id)
        categoryRepository.delete(toDeleteCategory)
    }

    fun updateCategory(categoryDto: CategoryDto, id: UUID): Category {
        return categoryRepository.findById(id).map {
            val save = categoryRepository.save(
                Category(
                    id = it.id,
                    categoryName = categoryDto.categoryName,
                    transactions = it.transactions
                )
            )
            Category(
                id = save.id,
                categoryName = save.categoryName,
                transactions = save.transactions
            )
        }.orElseGet(null)
    }

    fun addTransactions(transactions: List<UUID>, categoryId: UUID): Category {
        val transactionsToBeAdded = transactions.map {
            transactionService.getTransactionById(it)
        }

        return categoryRepository.findById(categoryId).map {
            val save = categoryRepository.save(
                Category(
                    id = it.id,
                    categoryName = it.categoryName,
                    transactions = it.transactions.union(transactionsToBeAdded).toList(),
                    parentCategory = it.parentCategory,
                    childCategory = it.childCategory,
                    targetBudget = it.targetBudget
                )
            )
            Category(
                id = save.id,
                categoryName = save.categoryName,
                transactions = save.transactions.union(transactionsToBeAdded).toList(),
                parentCategory = save.parentCategory,
                childCategory = save.childCategory,
                targetBudget = save.targetBudget
            )
        }.orElseGet(null)
    }

}