package com.xpromus.okanefinancespring.core.transactions.transaction

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.sorting.categories.Category
import com.xpromus.okanefinancespring.core.payees.Payee
import com.xpromus.okanefinancespring.core.sorting.tags.Tag
import java.util.*

data class TransactionDto(
    val transactionName: String,
    val doneDate: Date,
    val finishedDate: Date?,
    val amount: Long,
    val accountId: String,
    val payeeId: String,
    val categoryId: String?,
    val tagIds: List<String>,
)

data class TransactionClasses(
    val account: Account,
    val payee: Payee,
    val category: Category?,
    val tags: List<Tag>,
)