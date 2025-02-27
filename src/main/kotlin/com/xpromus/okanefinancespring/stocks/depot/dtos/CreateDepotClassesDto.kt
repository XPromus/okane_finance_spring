package com.xpromus.okanefinancespring.stocks.depot.dtos

import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.owners.Owner

data class CreateDepotClassesDto(
    val institute: Institute,
    val owner: Owner
)
