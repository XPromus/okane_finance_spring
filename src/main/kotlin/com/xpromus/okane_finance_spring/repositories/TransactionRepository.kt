package com.xpromus.okane_finance_spring.repositories

import com.xpromus.okane_finance_spring.entities.Transaction
import org.springframework.data.domain.Limit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {
    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(:id IS NULL OR t.id = :id) AND " +
                "(:transactionName IS NULL OR t.transactionName = :transactionName)"
    )
    fun findTransactionByIdAndTransactionName(
        id: UUID?,
        transactionName: String?
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(t.doneDate > :lowerRange) AND " +
                "(t.doneDate < :upperRange)"
    )
    fun findTransactionsByDoneDateRange(
        lowerRange: Date,
        upperRange: Date
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(t.finishedDate > :lowerRange) AND " +
                "(t.finishedDate < :upperRange)"
    )
    fun findTransactionsByFinishedDateRange(
        lowerRange: Date,
        upperRange: Date
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(t.amount > :lowerRange) AND " +
                "(t.amount < :upperRange)"
    )
    fun findTransactionsByAmountRange(
        lowerRange: Long,
        upperRange: Long
    ): MutableList<Transaction>

}