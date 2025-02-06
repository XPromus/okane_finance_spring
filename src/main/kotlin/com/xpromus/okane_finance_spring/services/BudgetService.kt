package com.xpromus.okane_finance_spring.services

import com.xpromus.okane_finance_spring.dto.BudgetDto
import com.xpromus.okane_finance_spring.dto.convertBudgetDtoToBudget
import com.xpromus.okane_finance_spring.entities.Budget
import com.xpromus.okane_finance_spring.exceptions.EntityNotFoundException
import com.xpromus.okane_finance_spring.repositories.BudgetRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BudgetService @Autowired constructor(
    private val budgetRepository: BudgetRepository
) {

    fun getBudgetById(id: UUID): Budget {
        return budgetRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Budget with id $id could not be found")
        }
    }

    fun getAllBudgets(
        id: UUID?, budgetName: String?, maxValue: Long?
    ): List<Budget> {
        if ((id ?: budgetName ?: maxValue) != null) {
            return budgetRepository.findBudgetByIdAndBudgetNameAndMaxValue(
                id, budgetName, maxValue
            )
        }

        return budgetRepository.findAll()
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