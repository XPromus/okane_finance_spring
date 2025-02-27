package com.xpromus.okanefinancespring.stocks.depot.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.depot.dtos.EditDepotClassesDto

fun fromEditDepotDto(depot: Depot, editDepotClassesDto: EditDepotClassesDto): Depot {
    return Depot(
        institute = editDepotClassesDto.institute ?: depot.institute,
        owner = editDepotClassesDto.owner ?: depot.owner
    )
}
