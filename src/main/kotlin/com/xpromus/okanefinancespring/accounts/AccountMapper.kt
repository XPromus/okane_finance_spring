package com.xpromus.okanefinancespring.accounts

import com.xpromus.okanefinancespring.owners.Owner

fun convertAccountDtoToAccount(
    accountDto: AccountDto,
    owner: Owner,
): Account {
    return Account(
        accountName = accountDto.accountName,
        startingBalance = accountDto.startingBalance,
        institute = accountDto.institute,
        owner = owner,
    )
}
