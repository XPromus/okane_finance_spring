package com.xpromus.okane_finance_spring.services

import com.xpromus.okane_finance_spring.dto.CategoryDto
import com.xpromus.okane_finance_spring.dto.convertCategoryDtoToCategory
import com.xpromus.okane_finance_spring.entities.Category
import com.xpromus.okane_finance_spring.exceptions.EntityNotFoundException
import com.xpromus.okane_finance_spring.repositories.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService @Autowired constructor(
    private val categoryRepository: CategoryRepository
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
        return categoryRepository.save(convertCategoryDtoToCategory(this, categoryDto))
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

}