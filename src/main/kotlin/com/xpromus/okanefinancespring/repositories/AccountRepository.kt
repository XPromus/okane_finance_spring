package com.xpromus.okanefinancespring.repositories

import com.xpromus.okanefinancespring.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {
    @Query(
        "SELECT a FROM account a WHERE " +
                "(:id IS NULL OR a.id = :id) AND" +
                "(:accountName IS NULL OR a.accountName = :accountName)"
    )
    fun findBudgetsByFields(
        id: UUID?,
        accountName: String?
    ): MutableList<Account>
}