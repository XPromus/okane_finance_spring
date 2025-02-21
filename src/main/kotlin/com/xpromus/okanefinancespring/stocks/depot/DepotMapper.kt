package com.xpromus.okanefinancespring.stocks.depot

import com.xpromus.okanefinancespring.institute.Institute
import com.xpromus.okanefinancespring.owners.Owner

fun convertDepotDtoToDepot(
    owner: Owner,
    institute: Institute
): Depot {
    return Depot(
        owner = owner,
        institute = institute
    )
}