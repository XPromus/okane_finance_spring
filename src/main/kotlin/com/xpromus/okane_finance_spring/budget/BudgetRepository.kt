package com.xpromus.okane_finance_spring.budget

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BudgetRepository : JpaRepository<Budget, UUID> {

    @Query("SELECT b FROM budget b WHERE b.id = ?1")
    fun findBudgetById(id: UUID): Budget?

    @Query(
        "SELECT b FROM budget b WHERE " +
        "(:id IS NULL OR b.id = :id) AND" +
        "(:budgetName IS NULL OR b.budgetName = :budgetName) AND" +
        "(:maxValue IS NULL OR b.maxValue = :maxValue)"
    )
    fun findBudgetByIdAndBudgetNameAndMaxValue(
        id: UUID?,
        budgetName: String?,
        maxValue: Long?
    ): MutableList<Budget>

}