package com.xpromus.okanefinancespring.transactions.recurring

import com.xpromus.okanefinancespring.accounts.Account
import com.xpromus.okanefinancespring.sorting.categories.Category
import com.xpromus.okanefinancespring.payees.Payee
import com.xpromus.okanefinancespring.sorting.tags.Tag
import java.util.Date

data class RecurringTransactionDto(
    val transactionName: String,
    val amount: Long,
    val dayOfTheMonth: Int?,
    val monthInterval: Int?,
    val dayOfTheWeek: Int?,
    val weekInterval: Int?,
    val recurringUntil: Date?,
    val accountId: String,
    val payeeId: String,
    val categoryId: String?,
    val tagIds: List<String>
)

data class RecurringTransactionClasses(
    val account: Account,
    val payee: Payee,
    val category: Category?,
    val tags: List<Tag>
)