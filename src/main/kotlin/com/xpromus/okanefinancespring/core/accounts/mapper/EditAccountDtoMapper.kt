package com.xpromus.okanefinancespring.core.accounts.mapper

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.accounts.dtos.EditAccountDto
import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.owners.Owner

fun fromEditAccountDto(
    account: Account,
    editAccountDto: EditAccountDto,
    institute: Institute?,
    owner: Owner?
): Account {
    return Account(
        id = account.id,
        accountName = editAccountDto.accountName ?: account.accountName,
        startingBalance = editAccountDto.startingBalance ?: account.startingBalance,
        transactions = account.transactions,
        institute = institute ?: account.institute,
        owner = owner ?: account.owner
    )
}