package com.xpromus.okanefinancespring.sorting.categories

import org.springframework.http.HttpStatus
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(
        @PathVariable id: UUID
    ) {
        categoryService.deleteCategory(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCategory(
        @PathVariable id: UUID,
        @RequestBody categoryDto: CategoryDto
    ): Category {
        return categoryService.updateCategory(categoryDto, id)
    }

}