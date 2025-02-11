package com.xpromus.okanefinancespring.budgets

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
    ): List<Budget> {
        return budgetRepository.findBudgetsByFields(
            id,
            budgetName,
            maxValue
        )
    }

    fun createBudget(budgetDto: BudgetDto): Budget {
        return budgetRepository.save(convertBudgetDtoToBudget(budgetDto))
    }

    @Transactional
    fun deleteBudget(id: UUID) {
        val toDeleteBudget = getBudgetById(id)
        budgetRepository.delete(toDeleteBudget)
    }

    fun updateBudget(budgetDto: BudgetDto, id: UUID): Budget {
        return budgetRepository.findById(id).map {
            val save = budgetRepository.save(
                Budget(
                    id = it.id,
                    budgetName = budgetDto.budgetName,
                    maxValue = budgetDto.maxValue,
                    targetCategories = it.targetCategories
                )
            )
            Budget(
                id = save.id,
                budgetName = save.budgetName,
                maxValue = save.maxValue,
                targetCategories = save.targetCategories
            )
        }.orElseGet(null)
    }

}