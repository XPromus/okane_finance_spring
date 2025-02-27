package com.xpromus.okanefinancespring.stocks.depot.dtos

import java.util.UUID

data class GetDepotDto(
    val id: UUID,
    val depotName: String,
    val instituteID: UUID,
    val ownerID: UUID,
    val taxExemptionEntryID: UUID?,
    val stockOrderIDs: List<UUID>
)
