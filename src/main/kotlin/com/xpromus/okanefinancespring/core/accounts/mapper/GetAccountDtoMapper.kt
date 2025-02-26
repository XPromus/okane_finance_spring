package com.xpromus.okanefinancespring.core.accounts.mapper

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.accounts.dtos.GetAccountDto

fun toGetAccountDto(account: Account): GetAccountDto {
    return GetAccountDto(
        id = account.id!!,
        accountName = account.accountName,
        startingBalance = account.startingBalance,
        instituteID = account.institute.id!!,
        transactionIDs = account.transactions.map { it.id!! },
        ownerID = account.owner.id!!
    )
}