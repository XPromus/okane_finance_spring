package com.xpromus.okane_finance_spring.account

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
