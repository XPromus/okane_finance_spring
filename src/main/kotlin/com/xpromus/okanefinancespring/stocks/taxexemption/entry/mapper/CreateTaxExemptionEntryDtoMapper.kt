package com.xpromus.okanefinancespring.stocks.taxexemption.entry.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.TaxExemptionEntry
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.CreateTaxExemptionEntryDto

fun fromCreateTaxExemptionEntryDto(
    depot: Depot,
    createTaxExemptionEntryDto: CreateTaxExemptionEntryDto
): TaxExemptionEntry {
    return TaxExemptionEntry(
        taxValue = createTaxExemptionEntryDto.taxValue,
        depot = depot
    )
}
