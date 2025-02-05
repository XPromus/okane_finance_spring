package com.xpromus.okane_finance_spring.category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/categories")
class CategoryController @Autowired constructor(
    private val categoryService: CategoryService
) {

    @GetMapping
    fun getCategories(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "categoryName", required = false) categoryName: String?,
    ): ResponseEntity<List<Category>> {
        return ResponseEntity<List<Category>>(
            categoryService.getAllCategories(id, categoryName),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun createCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<Category> {
        return ResponseEntity<Category>(
            categoryService.createCategory(categoryDto),
            HttpStatus.OK
        )
    }

    @DeleteMapping
    fun deleteCategory(
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<String> {
        val toDeleteCategory = categoryService.getCategoryById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        categoryService.deleteCategory(toDeleteCategory)
        return ResponseEntity<String>(
            "Category with id: $id has been deleted",
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updateCategory(
        @RequestBody categoryDto: CategoryDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<Category> {
        return ResponseEntity<Category>(
            categoryService.updateCategory(categoryDto, id),
            HttpStatus.OK
        )
    }

}