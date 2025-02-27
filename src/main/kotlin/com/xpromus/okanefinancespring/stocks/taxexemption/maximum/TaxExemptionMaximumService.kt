package com.xpromus.okanefinancespring.stocks.taxexemption.maximum

import com.xpromus.okanefinancespring.stocks.taxexemption.entry.TaxExemptionEntryService
import org.springframework.stereotype.Service

@Service
class TaxExemptionMaximumService(
    private val taxExemptionEntryService: TaxExemptionEntryService
) {

    fun getMaximumStatus(): GetTaxExemptionMaximumDto {
        val allEntries = taxExemptionEntryService.getAllTaxExemptionEntries(
            id = null, value = null, depotID = null
        )
        val valueOfAllEntries = allEntries.sumOf { it.taxValue }
        return GetTaxExemptionMaximumDto(
            status = valueOfAllEntries <= MAX_TAX_EXEMPTION_VALUE,
            valueOfAllEntries = valueOfAllEntries,
            maxValue = MAX_TAX_EXEMPTION_VALUE
        )
    }

    companion object {
        const val MAX_TAX_EXEMPTION_VALUE: Int = 1000
    }
}