package com.xpromus.okanefinancespring.core.budgets

import com.xpromus.okanefinancespring.core.budgets.dtos.CreateBudgetDto
import com.xpromus.okanefinancespring.core.budgets.dtos.EditBudgetDto
import com.xpromus.okanefinancespring.core.budgets.dtos.GetBudgetDto
import com.xpromus.okanefinancespring.core.budgets.mapper.fromCreateBudgetDto
import com.xpromus.okanefinancespring.core.budgets.mapper.fromEditBudgetDto
import com.xpromus.okanefinancespring.core.budgets.mapper.toGetBudgetDto
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class BudgetService(
    private val budgetRepository: BudgetRepository,
) {
    fun getBudgetById(id: UUID): Budget {
        return budgetRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Budget", id.toString())
            )
        }
    }

    fun getAllBudgets(
        id: UUID?, budgetName: String?, maxValue: Long?,
    ): List<GetBudgetDto> {
        val budgetsToReturn = budgetRepository.findBudgetsByFields(
            id,
            budgetName,
            maxValue
        )
        return budgetsToReturn.map { toGetBudgetDto(it) }
    }

    fun createBudget(createBudgetDto: CreateBudgetDto): GetBudgetDto {
        val newBudget = budgetRepository.save(fromCreateBudgetDto(createBudgetDto))
        return toGetBudgetDto(newBudget)
    }

    fun updateBudget(id: UUID, editBudgetDto: EditBudgetDto): GetBudgetDto {
        return budgetRepository.findById(id).map {
            val save = budgetRepository.save(fromEditBudgetDto(it, editBudgetDto))
            toGetBudgetDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteBudget(id: UUID) {
        val toDeleteBudget = getBudgetById(id)
        budgetRepository.delete(toDeleteBudget)
    }
}