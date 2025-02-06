package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.entities.Account

fun convertAccountDtoToAccount(
    accountDto: AccountDto
): Account {
    return Account(
        accountName = accountDto.accountName
    )
}
