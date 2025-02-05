package com.xpromus.okane_finance_spring.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {
    @Query("SELECT a FROM account a WHERE a.id = ?1")
    fun findAccountById(id: UUID): Account?

    @Query(
        "SELECT a FROM account a WHERE " +
        "(:id IS NULL OR a.id = :id) AND" +
        "(:accountName IS NULL OR a.accountName = :accountName)"
    )
    fun findAccountsByIdAndAccountName(
        id: UUID?,
        accountName: String?
    ): MutableList<Account>
}