package com.xpromus.okanefinancespring.core.transaction.mapper

import com.xpromus.okanefinancespring.core.transaction.Transaction
import com.xpromus.okanefinancespring.core.transaction.dtos.CreateTransactionDto
import com.xpromus.okanefinancespring.core.transaction.dtos.TransactionClassesDto

fun fromCreateTransactionDto(
    transactionClassesDto: TransactionClassesDto,
    createTransactionDto: CreateTransactionDto
): Transaction {
    return Transaction(
        transactionName = createTransactionDto.transactionName,
        doneDate = createTransactionDto.doneDate,
        finishedDate = createTransactionDto.finishedDate,
        amount = createTransactionDto.amount,
        targetAccount = transactionClassesDto.account,
        targetPayee = transactionClassesDto.payee,
        targetCategory = transactionClassesDto.category
    )
}
