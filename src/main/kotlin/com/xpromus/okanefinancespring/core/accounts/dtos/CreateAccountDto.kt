package com.xpromus.okanefinancespring.core.accounts.dtos

import java.util.UUID

data class CreateAccountDto(
    val accountName: String,
    val startingBalance: Long,
    val instituteID: UUID,
    val ownerID: UUID
)
