package com.xpromus.okanefinancespring.controllers

import com.xpromus.okanefinancespring.dto.BudgetDto
import com.xpromus.okanefinancespring.entities.Budget
import com.xpromus.okanefinancespring.services.BudgetService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/budgets")
class BudgetController(
    private val budgetService: BudgetService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getBudgets(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "budgetName", required = false) budgetName: String?,
        @RequestParam(name = "maxValue", required = false) maxValue: Long?
    ): List<Budget> {
        return budgetService.getAllBudgets(id, budgetName, maxValue)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBudget(@RequestBody budgetDto: BudgetDto): Budget {
        return budgetService.createBudget(budgetDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBudget(
        @PathVariable id: UUID,
    ) {
        budgetService.deleteBudget(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateBudget(
        @PathVariable id: UUID,
        @RequestBody budgetDto: BudgetDto
    ): Budget {
        return budgetService.updateBudget(budgetDto, id)
    }

}