package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.entities.Account
import com.xpromus.okanefinancespring.entities.Owner

fun convertAccountDtoToAccount(
    accountDto: AccountDto,
    owner: Owner
): Account {
    return Account(
        accountName = accountDto.accountName,
        owner = owner
    )
}
