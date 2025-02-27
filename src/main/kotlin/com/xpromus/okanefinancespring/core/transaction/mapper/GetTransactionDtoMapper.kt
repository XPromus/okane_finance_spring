package com.xpromus.okanefinancespring.core.transaction.mapper

import com.xpromus.okanefinancespring.core.transaction.Transaction
import com.xpromus.okanefinancespring.core.transaction.dtos.GetTransactionDto
import java.util.*

fun toGetTransactionDto(transaction: Transaction): GetTransactionDto {
    val targetCategoryID: UUID? = if (transaction.targetCategory == null) {
        null
    } else {
        transaction.targetCategory!!.id!!
    }

    return GetTransactionDto(
        id = transaction.id!!,
        transactionName = transaction.transactionName,
        doneDate = transaction.doneDate,
        finishedDate = transaction.finishedDate,
        amount = transaction.amount,
        targetAccountID = transaction.targetAccount.id!!,
        targetPayeeID = transaction.targetPayee.id!!,
        targetCategory = targetCategoryID
    )
}
