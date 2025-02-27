package com.xpromus.okanefinancespring.stocks.depot.dtos

import java.util.*

data class EditDepotDto(
    val instituteID: UUID?,
    val ownerID: UUID?,
    val taxExemptionEntryID: UUID?
)
