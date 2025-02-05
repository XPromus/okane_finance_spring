package com.xpromus.okane_finance_spring.transaction

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM transaction t WHERE t.id = ?1")
    fun findTransactionById(id: UUID): Transaction?

    @Query(
        "SELECT t FROM transaction t WHERE " +
        "(:id IS NULL OR t.id = :id) AND" +
        "(:transactionName IS NULL OR t.transactionName = :transactionName)"
    )
    fun findTransactionByIdAndTransactionName(
        id: UUID?,
        transactionName: String?
    ): MutableList<Transaction>

}