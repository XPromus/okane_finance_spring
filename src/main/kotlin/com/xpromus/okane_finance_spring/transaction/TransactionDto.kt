package com.xpromus.okane_finance_spring.transaction

import com.xpromus.okane_finance_spring.account.AccountService
import java.util.Date
import java.util.UUID

data class TransactionDto (
    val doneDate: Date,
    val finishedDate: Date,
    val amount: Long,
    val targetAccount: UUID
)

fun covertTransactionDtoToTransaction(transactionDto: TransactionDto, accountService: AccountService): Transaction {
    val targetAccount = accountService.getAccountById(transactionDto.targetAccount)!!
    return Transaction(
        id = UUID.randomUUID(),
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
        targetAccount = targetAccount
    )
}

