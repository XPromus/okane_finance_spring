package com.xpromus.okanefinancespring.core.payees.dtos

import java.util.*

data class GetPayeeDto(
    val id: UUID,
    val payeeName: String,
    val transactionIDs: List<UUID>
)
