package com.xpromus.okane_finance_spring.transaction

import com.xpromus.okane_finance_spring.account.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService @Autowired constructor(
    private val accountRepository: AccountRepository
) {
}