package com.xpromus.okanefinancespring.core.budgets

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BudgetRepository : JpaRepository<Budget, UUID> {
    @Query(
        "SELECT b FROM budget b WHERE " +
                "(:id IS NULL OR b.id = :id) AND" +
                "(:budgetName IS NULL OR b.budgetName = :budgetName) AND" +
                "(:maxValue IS NULL OR b.maxValue = :maxValue)"
    )
    fun findBudgetsByFields(
        id: UUID?,
        budgetName: String?,
        maxValue: Long?
    ): MutableList<Budget>

}