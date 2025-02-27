package com.xpromus.okanefinancespring.core.transaction.dtos

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.payees.Payee
import com.xpromus.okanefinancespring.core.categories.Category

data class TransactionClassesDto(
    val account: Account,
    val payee: Payee,
    val category: Category?
)
