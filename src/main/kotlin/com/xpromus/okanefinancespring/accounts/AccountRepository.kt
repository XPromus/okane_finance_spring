package com.xpromus.okanefinancespring.accounts

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {
    @Query(
        "SELECT a FROM account a WHERE " +
                "(:id IS NULL OR a.id = :id) AND" +
                "(:accountName IS NULL OR a.accountName = :accountName) AND " +
                "(:startingBalance IS NULL OR a.startingBalance = :startingBalance) AND " +
                "(:institute IS NULL OR a.institute = :institute)"
    )
    fun findBudgetsByFields(
        id: UUID?,
        accountName: String?,
        startingBalance: Long?,
        institute: String?
    ): MutableList<Account>
}