package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.*

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
    targetAccount: Account,
    targetPayee: Payee,
    targetCategory: Category?,
    targetTags: List<Tag>
): Transaction {
    return Transaction(
        transactionName = transactionDto.transactionName,
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
        targetAccount = targetAccount,
        targetPayee = targetPayee,
        targetCategory = targetCategory,
        targetTags = targetTags
    )
}
