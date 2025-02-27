package com.xpromus.okanefinancespring.core.sorting.tags.dtos

import java.util.UUID

data class GetTagDto(
    val id: UUID,
    val tagName: String,
    val transactionIDs: List<UUID>
)
