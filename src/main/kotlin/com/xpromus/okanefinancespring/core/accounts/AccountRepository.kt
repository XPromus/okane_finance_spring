package com.xpromus.okanefinancespring.core.accounts

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
                "(:instituteID IS NULL OR a.institute.id = :instituteID) AND " +
                "(:ownerID IS NULL OR a.owner.id = :ownerID)"
    )
    fun findBudgetsByFields(
        id: UUID?,
        accountName: String?,
        startingBalance: Long?,
        instituteID: UUID?,
        ownerID: UUID?
    ): MutableList<Account>
}