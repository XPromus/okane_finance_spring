package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface TaxExemptionEntryRepository : JpaRepository<TaxExemptionEntry, UUID> {

    @Query(
        "SELECT t FROM tax_exemption_entry t WHERE " +
                "(:id IS NULL OR t.id = :id) AND " +
                "(:value IS NULL OR t.value = :value)"
    )
    fun findTaxExemptionEntriesByFields(
        id: UUID?,
        value: Int?
    ): MutableList<TaxExemptionEntry>

}