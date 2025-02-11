package com.xpromus.okanefinancespring.transactions.transaction

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
    transactionClasses: TransactionClasses,
): Transaction {
    return Transaction(
        transactionName = transactionDto.transactionName,
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
        targetAccount = transactionClasses.account,
        targetPayee = transactionClasses.payee,
        targetCategory = transactionClasses.category,
        targetTags = transactionClasses.tags
    )
}
