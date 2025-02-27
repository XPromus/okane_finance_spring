package com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos

import java.util.*

data class CreateTaxExemptionEntryDto(
    val taxValue: Int,
    val depotID: UUID
)
