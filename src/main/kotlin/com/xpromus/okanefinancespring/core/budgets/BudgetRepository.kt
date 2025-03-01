package com.xpromus.okanefinancespring.core.budgets

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BudgetRepository : JpaRepository<Budget, UUID> {
    @Query(
        "SELECT b FROM budget b WHERE " +
                "(cast(:id as uuid) IS NULL OR b.id = :id) AND" +
                "(cast(:budgetName as string) IS NULL OR b.budgetName = :budgetName) AND" +
                "(cast(:maxValue as long) IS NULL OR b.maxValue = :maxValue)"
    )
    fun findBudgetsByFields(
        id: UUID?,
        budgetName: String?,
        maxValue: Long?
    ): MutableList<Budget>

}