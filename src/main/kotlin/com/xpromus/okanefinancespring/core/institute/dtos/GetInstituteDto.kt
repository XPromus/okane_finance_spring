package com.xpromus.okanefinancespring.core.institute.dtos

import java.util.UUID

data class GetInstituteDto(
    val id: UUID,
    val instituteName: String,
    val accountIDs: List<UUID>,
    val depotIDs: List<UUID>
)
