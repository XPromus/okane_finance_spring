package com.xpromus.okane_finance_spring.dto

import com.xpromus.okane_finance_spring.services.AccountService
import com.xpromus.okane_finance_spring.services.PayeeService
import com.xpromus.okane_finance_spring.services.CategoryService
import com.xpromus.okane_finance_spring.entities.Transaction
import com.xpromus.okane_finance_spring.services.TagService
import java.util.Date
import java.util.UUID

data class TransactionDto(
    val transactionName: String,
    val doneDate: Date,
    val finishedDate: Date,
    val amount: Long,
    val targetAccount: UUID,
    val targetPayee: UUID,
    val targetCategory: UUID,
    val targetTags: List<UUID>
)

fun covertTransactionDtoToTransaction(
    transactionDto: TransactionDto,
    accountService: AccountService,
    payeeService: PayeeService,
    categoryService: CategoryService,
    tagService: TagService
): Transaction {
    val targetAccount = accountService.getAccountById(transactionDto.targetAccount)!!
    val targetPayee = payeeService.getPayeeById(transactionDto.targetPayee)!!
    val targetCategory = categoryService.getCategoryById(transactionDto.targetCategory)!!
    val targetTags = transactionDto.targetTags.map {
        tagService.getTagById(it)
    }
    return Transaction(
        id = UUID.randomUUID(),
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

