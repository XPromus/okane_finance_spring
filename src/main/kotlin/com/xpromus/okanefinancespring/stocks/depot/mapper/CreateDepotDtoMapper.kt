package com.xpromus.okanefinancespring.stocks.depot.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.depot.dtos.CreateDepotClassesDto

fun fromCreateDepotDto(
    createDepotClassesDto: CreateDepotClassesDto
): Depot {
    return Depot(
        institute = createDepotClassesDto.institute,
        owner = createDepotClassesDto.owner
    )
}
