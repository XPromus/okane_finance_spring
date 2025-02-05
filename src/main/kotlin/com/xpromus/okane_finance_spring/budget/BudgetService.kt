package com.xpromus.okane_finance_spring.budget

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BudgetService @Autowired constructor(
    private val budgetRepository: BudgetRepository
) {

    fun getBudgetById(id: UUID): Budget? {
        return budgetRepository.findBudgetById(id)
    }

    fun getAllBudgets(
        id: UUID?,
        budgetName: String?,
        maxValue: Long?
    ): List<Budget> {
        if ((id ?: budgetName ?: maxValue) != null) {
            return budgetRepository.findBudgetByIdAndBudgetNameAndMaxValue(
                id,
                budgetName,
                maxValue
            )
        }

        return budgetRepository.findAll()
    }

    fun createBudget(budgetDto: BudgetDto): Budget {
        return budgetRepository.save(convertBudgetDtoToBudget(budgetDto))
    }

    @Transactional
    fun deleteBudget(budget: Budget) {
        budgetRepository.delete(budget)
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