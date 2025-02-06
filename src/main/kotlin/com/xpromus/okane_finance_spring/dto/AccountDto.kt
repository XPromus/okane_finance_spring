package com.xpromus.okane_finance_spring.dto

import com.xpromus.okane_finance_spring.entities.Account
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
