package com.xpromus.okanefinancespring.stocks.depot.dtos

import java.util.*

data class CreateDepotDto(
    val instituteID: UUID,
    val depotName: String,
    val ownerID: UUID,
    val taxExemptionEntryID: UUID?
)
