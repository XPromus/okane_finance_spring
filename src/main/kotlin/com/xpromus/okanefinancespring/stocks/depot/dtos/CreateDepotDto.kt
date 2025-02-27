package com.xpromus.okanefinancespring.stocks.depot.dtos

import java.util.*

data class CreateDepotDto(
    val instituteID: UUID,
    val ownerID: UUID,
    val taxExemptionEntryID: UUID?
)
