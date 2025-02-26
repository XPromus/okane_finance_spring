package com.xpromus.okanefinancespring.core.owners

import java.util.*

data class OwnerDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: Date?,
    val accountIDs: List<String>,
    val depotIDs: List<String>,
)
