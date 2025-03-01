package com.xpromus.okanefinancespring.core.accounts

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {
    @Query(
        "SELECT a FROM account a WHERE " +
                "(cast(:id as uuid) IS NULL OR a.id = :id) AND" +
                "(cast(:accountName as string) IS NULL OR a.accountName = :accountName) AND " +
                "(cast(:startingBalance as long) IS NULL OR a.startingBalance = :startingBalance) AND " +
                "(cast(:instituteID as uuid) IS NULL OR a.institute.id = :instituteID) AND " +
                "(cast(:ownerID as uuid) IS NULL OR a.owner.id = :ownerID)"
    )
    fun findBudgetsByFields(
        id: UUID?,
        accountName: String?,
        startingBalance: Long?,
        instituteID: UUID?,
        ownerID: UUID?
    ): MutableList<Account>
}