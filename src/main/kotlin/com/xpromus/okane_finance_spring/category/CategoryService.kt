package com.xpromus.okane_finance_spring.category

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService @Autowired constructor(
    private val categoryRepository: CategoryRepository
) {

    fun getCategoryById(id: UUID): Category? {
        return categoryRepository.findCategoryById(id)
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
    fun deleteCategory(category: Category) {
        categoryRepository.delete(category)
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