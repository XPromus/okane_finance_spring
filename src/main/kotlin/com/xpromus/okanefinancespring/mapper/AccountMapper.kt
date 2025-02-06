package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.entities.Account
import java.util.*

fun convertAccountDtoToAccount(accountDto: AccountDto): Account {
    return Account(
        id = UUID.randomUUID(),
        accountName = accountDto.accountName
    )
}
