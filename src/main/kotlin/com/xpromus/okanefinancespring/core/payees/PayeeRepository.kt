package com.xpromus.okanefinancespring.core.payees

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PayeeRepository : JpaRepository<Payee, UUID> {
    @Query(
        "SELECT p FROM payee p WHERE " +
                "(:id IS NULL OR p.id = :id) AND" +
                "(:payeeName IS NULL OR p.payeeName = :payeeName)"
    )
    fun findPayeesByFields(
        id: UUID?,
        payeeName: String?
    ): MutableList<Payee>
}