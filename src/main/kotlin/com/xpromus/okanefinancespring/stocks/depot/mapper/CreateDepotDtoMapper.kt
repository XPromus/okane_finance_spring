package com.xpromus.okanefinancespring.stocks.depot.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.depot.dtos.CreateDepotClassesDto
import com.xpromus.okanefinancespring.stocks.depot.dtos.CreateDepotDto

fun fromCreateDepotDto(
    createDepotDto: CreateDepotDto,
    createDepotClassesDto: CreateDepotClassesDto
): Depot {
    return Depot(
        depotName = createDepotDto.depotName,
        institute = createDepotClassesDto.institute,
        owner = createDepotClassesDto.owner
    )
}
