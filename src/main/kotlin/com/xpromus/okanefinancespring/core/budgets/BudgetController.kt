package com.xpromus.okanefinancespring.core.budgets

import com.xpromus.okanefinancespring.core.budgets.dtos.CreateBudgetDto
import com.xpromus.okanefinancespring.core.budgets.dtos.EditBudgetDto
import com.xpromus.okanefinancespring.core.budgets.dtos.GetBudgetDto
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
    ): List<GetBudgetDto> {
        return budgetService.getAllBudgets(id, budgetName, maxValue)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBudget(@RequestBody createBudgetDto: CreateBudgetDto): GetBudgetDto {
        return budgetService.createBudget(createBudgetDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateBudget(
        @PathVariable id: UUID,
        @RequestBody editBudgetDto: EditBudgetDto
    ): GetBudgetDto {
        return budgetService.updateBudget(id, editBudgetDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBudget(
        @PathVariable id: UUID,
    ) {
        budgetService.deleteBudget(id)
    }
}