package com.xpromus.okanefinancespring.stocks.depot.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.depot.dtos.GetDepotDto

fun toGetDepotDto(depot: Depot): GetDepotDto {
    return GetDepotDto(
        id = depot.id!!,
        depotName = depot.depotName,
        instituteID = depot.institute.id!!,
        ownerID = depot.owner.id!!,
        taxExemptionEntryID = depot.taxExemptionEntry?.id!!,
        stockOrderIDs = depot.stockOrders.map { it.id!! }
    )
}
