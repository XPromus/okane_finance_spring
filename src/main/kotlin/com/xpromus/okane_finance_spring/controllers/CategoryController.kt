package com.xpromus.okane_finance_spring.controllers

import com.xpromus.okane_finance_spring.dto.CategoryDto
import com.xpromus.okane_finance_spring.services.CategoryService
import com.xpromus.okane_finance_spring.entities.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCategories(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "categoryName", required = false) categoryName: String?,
    ): List<Category> {
        return categoryService.getAllCategories(id, categoryName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(@RequestBody categoryDto: CategoryDto): Category {
        return categoryService.createCategory(categoryDto)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(
        @RequestParam(name = "id", required = true) id: UUID
    ) {
        categoryService.deleteCategory(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateCategory(
        @RequestBody categoryDto: CategoryDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): Category {
        return categoryService.updateCategory(categoryDto, id)
    }

}