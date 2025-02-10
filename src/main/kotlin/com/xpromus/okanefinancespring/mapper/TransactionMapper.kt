package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.TransactionClasses
import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.*

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
    transactionClasses: TransactionClasses,
//    targetAccount: Account,
//    targetPayee: Payee,
//    targetCategory: Category?,
//    targetTags: List<Tag>
): Transaction {
    return Transaction(
        transactionName = transactionDto.transactionName,
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
        isRecurring = transactionDto.isRecurring,
        recurringDate = transactionDto.recurringDate,
        targetAccount = transactionClasses.account,
        targetPayee = transactionClasses.payee,
        targetCategory = transactionClasses.category,
        targetTags = transactionClasses.tags
    )
}
