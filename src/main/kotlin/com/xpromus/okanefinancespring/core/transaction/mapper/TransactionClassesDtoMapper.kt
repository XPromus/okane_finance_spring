package com.xpromus.okanefinancespring.core.transaction.mapper

import com.xpromus.okanefinancespring.core.accounts.AccountService
import com.xpromus.okanefinancespring.core.payees.PayeeService
import com.xpromus.okanefinancespring.core.categories.Category
import com.xpromus.okanefinancespring.core.categories.CategoryService
import com.xpromus.okanefinancespring.core.transaction.dtos.TransactionClassesDto
import java.util.*

fun toTransactionClassesDto(
    accountID: UUID,
    payeeID: UUID,
    categoryID: UUID?,
    accountService: AccountService,
    payeeService: PayeeService,
    categoryService: CategoryService
): TransactionClassesDto {
    val targetAccount = accountService.getAccountById(accountID)
    val targetPayee = payeeService.getPayeeById(payeeID)
    val targetCategory: Category? = if (categoryID == null) {
        null
    } else {
        categoryService.getCategoryById(categoryID)
    }

    return TransactionClassesDto(
        account = targetAccount,
        payee = targetPayee,
        category = targetCategory
    )
}