package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaxExemptionEntryRepository : JpaRepository<TaxExemptionEntry, UUID> {

    @Query(
        "SELECT t FROM tax_exemption_entry t WHERE " +
                "(:id IS NULL OR t.id = :id) AND " +
                "(:taxValue IS NULL OR t.taxValue = :taxValue) AND " +
                "(:depotID IS NULL OR t.depot.id = :depotID)"
    )
    fun findTaxExemptionEntriesByFields(
        id: UUID?,
        taxValue: Int?,
        depotID: UUID?
    ): MutableList<TaxExemptionEntry>

}