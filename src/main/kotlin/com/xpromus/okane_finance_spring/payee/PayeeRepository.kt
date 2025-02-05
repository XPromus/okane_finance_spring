package com.xpromus.okane_finance_spring.payee

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PayeeRepository : JpaRepository<Payee, UUID>{
    @Query("SELECT a FROM account a WHERE a.id = ?1")
    fun findPayeeById(id: UUID): Payee?

    @Query(
        "SELECT p FROM payee p WHERE " +
        "(:id IS NULL OR p.id = :id) AND" +
        "(:payeeName IS NULL OR p.payeeName = :payeeName)"
    )
    fun findPayeeByParameters(
        id: UUID?,
        payeeName: String?
    ): MutableList<Payee>
}