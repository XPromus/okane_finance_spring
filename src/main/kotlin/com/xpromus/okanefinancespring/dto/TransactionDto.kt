package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Account
import com.xpromus.okanefinancespring.entities.Category
import com.xpromus.okanefinancespring.entities.Payee
import com.xpromus.okanefinancespring.entities.Tag
import java.util.*

data class TransactionDto(
    val transactionName: String,
    val doneDate: Date,
    val finishedDate: Date?,
    val amount: Long,
    val isRecurring: Boolean,
    val recurringDate: Date?,
    val accountId: String,
    val payeeId: String,
    val categoryId: String?,
    val tagIds: List<String>
)

data class TransactionClasses(
    val account: Account,
    val payee: Payee,
    val category: Category?,
    val tags: List<Tag>
)