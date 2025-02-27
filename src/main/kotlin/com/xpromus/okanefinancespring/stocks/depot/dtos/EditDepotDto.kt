package com.xpromus.okanefinancespring.stocks.depot.dtos

import java.util.*

data class EditDepotDto(
    val depotName: String?,
    val instituteID: UUID?,
    val ownerID: UUID?,
    val taxExemptionEntryID: UUID?
)
