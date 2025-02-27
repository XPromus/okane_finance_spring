package com.xpromus.okanefinancespring.core.transaction.dtos

import java.util.Date
import java.util.UUID

data class GetTransactionDto(
    val id: UUID,
    val transactionName: String,
    val doneDate: Date,
    val finishedDate: Date?,
    val amount: Long,
    val targetAccountID: UUID,
    val targetPayeeID: UUID,
    val targetCategory: UUID?
)
