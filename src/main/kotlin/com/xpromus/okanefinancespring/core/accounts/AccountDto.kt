package com.xpromus.okanefinancespring.core.accounts

data class AccountDto(
    val id: String = "",
    val accountName: String = "",
    val startingBalance: Long = 0,
    val instituteId: String = "",
    val transactionIds: List<String> = emptyList(),
    val ownerId: String = "",
)