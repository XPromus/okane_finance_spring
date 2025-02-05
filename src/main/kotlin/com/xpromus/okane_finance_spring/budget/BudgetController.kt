package com.xpromus.okane_finance_spring.budget

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/budgets")
class BudgetController @Autowired constructor(
    private val budgetService: BudgetService
) {

    @GetMapping
    fun getBudgets(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "budgetName", required = false) budgetName: String?,
        @RequestParam(name = "maxValue", required = false) maxValue: Long?
    ): ResponseEntity<List<Budget>> {
        return ResponseEntity<List<Budget>>(
            budgetService.getAllBudgets(id, budgetName, maxValue),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun createBudget(@RequestBody budgetDto: BudgetDto): ResponseEntity<Budget> {
        return ResponseEntity<Budget>(
            budgetService.createBudget(budgetDto),
            HttpStatus.OK
        )
    }

    @DeleteMapping
    fun deleteBudget(
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<String> {
        val toDeleteBudget = budgetService.getBudgetById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        budgetService.deleteBudget(toDeleteBudget)
        return ResponseEntity<String>(
            "Budget with id: $id has been deleted",
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updateBudget(
        @RequestBody budgetDto: BudgetDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<Budget> {
        return ResponseEntity<Budget>(
            budgetService.updateBudget(budgetDto, id),
            HttpStatus.OK
        )
    }

}