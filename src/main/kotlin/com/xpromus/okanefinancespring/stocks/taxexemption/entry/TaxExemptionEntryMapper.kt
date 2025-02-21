package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import com.xpromus.okanefinancespring.stocks.depot.Depot

fun convertTaxExemptionEntryDtoToTaxExemptionEntry(
    taxExemptionEntryDto: TaxExemptionEntryDto,
    depot: Depot
): TaxExemptionEntry {
    return TaxExemptionEntry(
        value = taxExemptionEntryDto.value,
        depot = depot
    )
}