package com.xpromus.okanefinancespring.dto

import java.util.*

data class TransactionDto(
    val transactionName: String,
    val doneDate: Date,
    val finishedDate: Date,
    val amount: Long,
)
