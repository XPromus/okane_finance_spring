package com.xpromus.okanefinancespring.core.transaction.mapper

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.payees.Payee
import com.xpromus.okanefinancespring.core.categories.Category
import com.xpromus.okanefinancespring.core.transaction.Transaction
import com.xpromus.okanefinancespring.core.transaction.dtos.EditTransactionDto

fun fromEditTransactionDto(
    transaction: Transaction,
    targetAccount: Account?,
    targetPayee: Payee?,
    targetCategory: Category?,
    editTransactionDto: EditTransactionDto
): Transaction {
    return Transaction(
        id = transaction.id,
        transactionName = editTransactionDto.transactionName ?: transaction.transactionName,
        doneDate = editTransactionDto.doneDate ?: transaction.doneDate,
        finishedDate = editTransactionDto.finishedDate ?: transaction.finishedDate,
        amount = editTransactionDto.amount ?: transaction.amount,
        targetAccount = targetAccount ?: transaction.targetAccount,
        targetPayee = targetPayee ?: transaction.targetPayee,
        targetCategory = targetCategory ?: transaction.targetCategory
    )
}
