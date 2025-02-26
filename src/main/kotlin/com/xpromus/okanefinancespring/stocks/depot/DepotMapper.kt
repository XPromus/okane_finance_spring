package com.xpromus.okanefinancespring.stocks.depot

import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.owners.Owner

fun convertDepotDtoToDepot(
    owner: Owner,
    institute: Institute
): Depot {
    return Depot(
        owner = owner,
        institute = institute
    )
}