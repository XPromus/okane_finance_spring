package com.xpromus.okanefinancespring.core.transaction.dtos

import java.util.*

data class EditTransactionDto(
    val transactionName: String?,
    val doneDate: Date?,
    val finishedDate: Date?,
    val amount: Long?,
    val targetAccountID: UUID?,
    val targetPayeeID: UUID?,
    val targetCategoryID: UUID?
)
