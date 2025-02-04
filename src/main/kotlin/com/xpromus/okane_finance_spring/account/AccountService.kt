package com.xpromus.okane_finance_spring.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService @Autowired constructor(
    private val accountRepository: AccountRepository
) {

    fun getAllAccounts(): List<Account> {
        return accountRepository.findAll()
    }

    fun createAccount(accountDto: AccountDto): Account {
        return accountRepository.save(convertAccountDtoToAccount(accountDto))
    }

}