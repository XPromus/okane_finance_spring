package com.xpromus.okanefinancespring.core.sorting.categories

import com.xpromus.okanefinancespring.core.sorting.categories.dtos.CreateCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.EditCategoryDto
import com.xpromus.okanefinancespring.core.sorting.categories.dtos.GetCategoryDto
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
    ): List<GetCategoryDto> {
        return categoryService.getAllCategories(id, categoryName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(@RequestBody createCategoryDto: CreateCategoryDto): GetCategoryDto {
        return categoryService.createCategory(createCategoryDto)
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
        @RequestBody editCategoryDto: EditCategoryDto
    ): GetCategoryDto {
        return categoryService.updateCategory(id, editCategoryDto)
    }
}