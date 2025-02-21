package com.xpromus.okanefinancespring.accounts

import com.xpromus.okanefinancespring.institute.Institute
import com.xpromus.okanefinancespring.owners.Owner

fun convertAccountDtoToAccount(
    accountDto: AccountDto,
    institute: Institute,
    owner: Owner,
): Account {
    return Account(
        accountName = accountDto.accountName,
        startingBalance = accountDto.startingBalance,
        institute = institute,
        owner = owner,
    )
}
