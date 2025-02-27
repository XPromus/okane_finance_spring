package com.xpromus.okanefinancespring.core.transaction

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {
    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(:id IS NULL OR t.id = :id) AND " +
                "(:transactionName IS NULL OR t.transactionName = :transactionName) AND" +
                "(:doneDate IS NULL OR t.doneDate = :doneDate) AND" +
                "(:finishedDate IS NULL OR t.finishedDate = :finishedDate) AND" +
                "(:amount IS NULL OR t.amount = :amount)"
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
                "(t.doneDate > :lowerRange) AND " +
                "(t.doneDate < :upperRange)"
    )
    fun findTransactionsByDoneDateRange(
        lowerRange: Date,
        upperRange: Date,
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(:targetAccount IS NULL OR t.targetAccount = :targetAccount) AND" +
                "(:lowerRange IS NULL OR t.finishedDate > :lowerRange) AND " +
                "(:upperRange IS NULL OR t.finishedDate < :upperRange)"
    )
    fun findTransactionsByFinishedDateRange(
        targetAccount: com.xpromus.okanefinancespring.core.accounts.Account?,
        lowerRange: Date?,
        upperRange: Date?,
    ): MutableList<Transaction>

    @Query(
        "SELECT t FROM transaction t WHERE " +
                "(t.amount > :lowerRange) AND " +
                "(t.amount < :upperRange)"
    )
    fun findTransactionsByAmountRange(
        lowerRange: Long,
        upperRange: Long,
    ): MutableList<Transaction>

}