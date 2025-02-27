package com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos

import java.util.UUID

data class GetTaxExemptionEntryDto(
    val id: UUID,
    val taxValue: Int,
    val depotID: UUID
)