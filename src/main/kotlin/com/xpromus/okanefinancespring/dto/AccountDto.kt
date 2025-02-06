package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Account
import java.util.*

data class AccountDto(
    val accountName: String = "",
)

fun convertAccountDtoToAccount(accountDto: AccountDto): Account {
    return Account(
        id = UUID.randomUUID(),
        accountName = accountDto.accountName
    )
}
