package com.xpromus.okanefinancespring.core.transaction

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {
    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(cast(:id as uuid) IS NULL OR t.id = :id) AND " +
                "(cast(:transactionName as string) IS NULL OR t.transactionName = :transactionName) AND" +
                "(cast(:doneDate as timestamp) IS NULL OR t.doneDate = :doneDate) AND" +
                "(cast(:finishedDate as timestamp) IS NULL OR t.finishedDate = :finishedDate) AND" +
                "(cast(:amount as long) IS NULL OR t.amount = :amount)"
    )
    fun findTransactionsByFields(
        id: UUID?,
        transactionName: String?,
        doneDate: Date?,
        finishedDate: Date?,
        amount: Long?,
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(t.doneDate > cast(:lowerRange as timestamp)) AND " +
                "(t.doneDate < cast(:upperRange as timestamp))"
    )
    fun findTransactionsByDoneDateRange(
        lowerRange: Date,
        upperRange: Date,
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(:lowerRange IS NULL OR t.finishedDate > cast(:lowerRange as timestamp)) AND " +
                "(:upperRange IS NULL OR t.finishedDate < cast(:upperRange as timestamp))"
    )
    fun findTransactionsByFinishedDateRange(
        lowerRange: Date?,
        upperRange: Date?,
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(t.amount > cast(:lowerRange as long)) AND " +
                "(t.amount < cast(:upperRange as long))"
    )
    fun findTransactionsByAmountRange(
        lowerRange: Long,
        upperRange: Long,
    ): MutableList<Transaction>

}