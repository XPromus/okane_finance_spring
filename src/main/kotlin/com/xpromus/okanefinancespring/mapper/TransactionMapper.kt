package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.Transaction
import java.util.*

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
): Transaction {
    return Transaction(
        id = UUID.randomUUID(),
        transactionName = transactionDto.transactionName,
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
    )
}
