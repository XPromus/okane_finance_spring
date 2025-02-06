package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.services.AccountService
import com.xpromus.okanefinancespring.services.PayeeService
import com.xpromus.okanefinancespring.services.CategoryService
import com.xpromus.okanefinancespring.entities.Transaction
import com.xpromus.okanefinancespring.services.TagService
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

