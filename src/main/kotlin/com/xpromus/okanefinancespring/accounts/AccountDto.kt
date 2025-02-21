package com.xpromus.okanefinancespring.accounts

data class AccountDto(
    val accountName: String = "",
    val ownerId: String = "",
    val startingBalance: Long = 0,
    val instituteId: String = "",
)