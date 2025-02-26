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

fun convertAccountToAccountDto(account: Account): AccountDto {
    return AccountDto(
        id = account.id.toString(),
        accountName = account.accountName,
        startingBalance = account.startingBalance,
        instituteId = account.institute.id.toString(),
        transactionIds = account.transactions.map { it.id.toString() },
        ownerId = account.owner.id.toString()
    )
}
