package com.xpromus.okanefinancespring.core.accounts.dtos

import java.util.*

data class GetAccountDto(
    val id: UUID,
    val accountName: String,
    val startingBalance: Long,
    val instituteID: UUID,
    val transactionIDs: List<UUID>,
    val ownerID: UUID
)
