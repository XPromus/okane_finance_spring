package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaxExemptionEntryRepository : JpaRepository<TaxExemptionEntry, UUID> {

    @Query(
        "SELECT t FROM tax_exemption_entry t WHERE " +
                "(cast(:id as uuid) IS NULL OR t.id = :id) AND " +
                "(cast(:taxValue as int) IS NULL OR t.taxValue = :taxValue) AND " +
                "(cast(:depotID as uuid) IS NULL OR t.depot.id = :depotID)"
    )
    fun findTaxExemptionEntriesByFields(
        id: UUID?,
        taxValue: Int?,
        depotID: UUID?
    ): MutableList<TaxExemptionEntry>

}