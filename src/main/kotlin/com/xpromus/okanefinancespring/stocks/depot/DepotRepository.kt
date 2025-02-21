package com.xpromus.okanefinancespring.stocks.depot

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DepotRepository : JpaRepository<Depot, UUID> {

    @Query("SELECT d FROM depot d WHERE " +
            "(:id IS NULL OR d.id = :id)"
    )
    fun findDepotsByField(
        id: UUID?
    ): MutableList<Depot>

}