package com.xpromus.okanefinancespring.stocks.taxexemption.entry.mapper

import com.xpromus.okanefinancespring.stocks.taxexemption.entry.TaxExemptionEntry
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.GetTaxExemptionEntryDto

fun toGetTaxExemptionEntryDto(taxExemptionEntry: TaxExemptionEntry): GetTaxExemptionEntryDto {
    return GetTaxExemptionEntryDto(
        id = taxExemptionEntry.id!!,
        taxValue = taxExemptionEntry.taxValue,
        depotID = taxExemptionEntry.depot.id!!
    )
}