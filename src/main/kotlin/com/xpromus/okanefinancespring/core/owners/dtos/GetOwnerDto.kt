package com.xpromus.okanefinancespring.core.owners.dtos

import java.util.Date
import java.util.UUID

data class GetOwnerDto(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val birthday: Date?,
    val accountIDs: List<UUID>,
    val depotIDs: List<UUID>
)
