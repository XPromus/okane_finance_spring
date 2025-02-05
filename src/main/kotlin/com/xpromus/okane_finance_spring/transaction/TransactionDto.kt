package com.xpromus.okane_finance_spring.transaction

import com.xpromus.okane_finance_spring.account.AccountService
import com.xpromus.okane_finance_spring.payee.PayeeService
import com.xpromus.okane_finance_spring.transaction.category.CategoryService
import java.util.Date
import java.util.UUID

data class TransactionDto (
    val transactionName: String,
    val doneDate: Date,
    val finishedDate: Date,
    val amount: Long,
    val targetAccount: UUID,
    val targetPayee: UUID,
    val targetCategory: UUID
)

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
    accountService: AccountService,
    payeeService: PayeeService,
    categoryService: CategoryService
): Transaction {
    val targetAccount = accountService.getAccountById(transactionDto.targetAccount)!!
    val targetPayee = payeeService.getPayeeById(transactionDto.targetPayee)!!
    val targetCategory = categoryService.getCategoryById(transactionDto.targetCategory)!!
    return Transaction(
        id = UUID.randomUUID(),
        transactionName = transactionDto.transactionName,
        doneDate = transactionDto.doneDate,
        finishedDate = transactionDto.finishedDate,
        amount = transactionDto.amount,
        targetAccount = targetAccount,
        targetPayee = targetPayee,
        targetCategory = targetCategory
    )
}

