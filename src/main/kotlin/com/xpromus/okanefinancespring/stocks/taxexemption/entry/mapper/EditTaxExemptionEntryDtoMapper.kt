package com.xpromus.okanefinancespring.stocks.taxexemption.entry.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.TaxExemptionEntry
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.EditTaxExemptionEntryDto

fun fromEditTaxExemptionEntryDto(
    taxExemptionEntry: TaxExemptionEntry,
    depot: Depot?,
    editTaxExemptionEntryDto: EditTaxExemptionEntryDto
): TaxExemptionEntry {
    return TaxExemptionEntry(
        taxValue = editTaxExemptionEntryDto.taxValue ?: taxExemptionEntry.taxValue,
        depot = depot ?: taxExemptionEntry.depot
    )
}
