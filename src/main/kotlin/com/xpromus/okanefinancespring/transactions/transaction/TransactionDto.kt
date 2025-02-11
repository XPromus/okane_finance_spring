package com.xpromus.okanefinancespring.transactions.transaction

import com.xpromus.okanefinancespring.accounts.Account
import com.xpromus.okanefinancespring.sorting.categories.Category
import com.xpromus.okanefinancespring.payees.Payee
import com.xpromus.okanefinancespring.sorting.tags.Tag
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