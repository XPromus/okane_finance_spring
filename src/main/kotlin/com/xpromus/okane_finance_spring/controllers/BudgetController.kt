package com.xpromus.okane_finance_spring.controllers

import com.xpromus.okane_finance_spring.dto.BudgetDto
import com.xpromus.okane_finance_spring.entities.Budget
import com.xpromus.okane_finance_spring.services.BudgetService
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBudget(
        @RequestParam(name = "id", required = true) id: UUID
    ) {
        budgetService.deleteBudget(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateBudget(
        @RequestBody budgetDto: BudgetDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): Budget {
        return budgetService.updateBudget(budgetDto, id)
    }

}