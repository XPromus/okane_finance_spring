package com.xpromus.okanefinancespring.stocks.depot

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DepotRepository : JpaRepository<Depot, UUID> {

    @Query("SELECT d FROM depot d WHERE " +
            "(:id IS NULL OR d.id = :id) AND " +
            "(:depotName IS NULL OR d.depotName = :depotName) AND " +
            "(:instituteID IS NULL OR d.institute.id = :instituteID) AND " +
            "(:ownerID IS NULL OR d.owner.id = :ownerID) AND " +
            "(:taxExemptionEntryID IS NULL OR d.taxExemptionEntry.id = :taxExemptionEntryID)"
    )
    fun findDepotsByField(
        id: UUID?,
        depotName: String?,
        instituteID: UUID?,
        ownerID: UUID?,
        taxExemptionEntryID: UUID?
    ): MutableList<Depot>

}