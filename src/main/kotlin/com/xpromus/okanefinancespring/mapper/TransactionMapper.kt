package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.Transaction

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
): Transaction {
    return Transaction(
        transactionName = transactionDto.transactionName,
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
    )
}
