package com.xpromus.okanefinancespring.core.accounts.mapper

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.accounts.dtos.CreateAccountDto
import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.owners.Owner

fun fromCreateAccountDto(
    account: CreateAccountDto,
    institute: Institute,
    owner: Owner
): Account {
    return Account(
        accountName = account.accountName,
        startingBalance = account.startingBalance,
        institute = institute,
        owner = owner
    )
}