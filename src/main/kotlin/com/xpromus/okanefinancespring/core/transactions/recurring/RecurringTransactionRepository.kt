package com.xpromus.okanefinancespring.core.transactions.recurring

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RecurringTransactionRepository : JpaRepository<RecurringTransaction, UUID> {

    @Query(
        "SELECT r FROM recurring_transaction r WHERE " +
                "(:id IS NULL OR r.id = :id) AND " +
                "(:transactionName IS NULL OR r.transactionName = :transactionName) AND " +
                "(:amount iS NULL OR r.amount = :amount) AND " +
                "(:dayOfTheMonth IS NULL OR r.dayOfTheMonth = :dayOfTheMonth) AND " +
                "(:monthInterval IS NULL OR r.monthInterval = :monthInterval) AND " +
                "(:dayOfTheWeek IS NULL OR r.dayOfTheWeek = :dayOfTheWeek) AND " +
                "(:weekInterval IS NULL OR r.weekInterval = :weekInterval) AND " +
                "(:recurringUntil IS NULL OR r.recurringUntil = :recurringUntil)"
    )
    fun findRecurringTransactionsByFields(
        id: UUID?,
        transactionName: String?,
        amount: Long?,
        dayOfTheMonth: Int?,
        monthInterval: Int?,
        dayOfTheWeek: Int?,
        weekInterval: Int?,
        recurringUntil: Date?
    ): MutableList<RecurringTransaction>

    @Query(
        "SELECT r FROM recurring_transaction r WHERE " +
                ":accountId IS NULL OR r.targetAccount.id = :accountId AND " +
                ":lowerRange IS NULL OR r.recurringUntil >= :lowerRange AND " +
                ":upperRnage IS NULL OR r.recurringUntil <= :upperRange"
    )
    fun findRecurringTransactionsByRecurringUntil(
        accountId: UUID?,
        lowerRange: Date?,
        upperRange: Date?
    ): MutableList<RecurringTransaction>

}