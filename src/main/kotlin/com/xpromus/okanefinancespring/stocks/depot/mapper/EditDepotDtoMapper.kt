package com.xpromus.okanefinancespring.stocks.depot.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.depot.dtos.EditDepotClassesDto
import com.xpromus.okanefinancespring.stocks.depot.dtos.EditDepotDto

fun fromEditDepotDto(
    depot: Depot,
    editDepotDto: EditDepotDto,
    editDepotClassesDto: EditDepotClassesDto
): Depot {
    return Depot(
        depotName = editDepotDto.depotName ?: depot.depotName,
        institute = editDepotClassesDto.institute ?: depot.institute,
        owner = editDepotClassesDto.owner ?: depot.owner
    )
}
