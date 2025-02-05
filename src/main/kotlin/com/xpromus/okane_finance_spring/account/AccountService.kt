package com.xpromus.okane_finance_spring.account

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService @Autowired constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountById(id: UUID): Account? {
        return accountRepository.findAccountById(id)
    }

    fun getAllAccounts(id: UUID?, accountName: String?): List<Account> {
        if ((id ?: accountName) != null) {
            return accountRepository.findAccountsByIdAndAccountName(id, accountName)
        }

        return accountRepository.findAll()
    }

    fun createAccount(accountDto: AccountDto): Account {
        return accountRepository.save(convertAccountDtoToAccount(accountDto))
    }

    @Transactional
    fun deleteAccount(account: Account) {
        accountRepository.delete(account)
    }

    fun updateAccount(accountDto: AccountDto, id: UUID): Account {
        return accountRepository.findById(id).map {
            val save = accountRepository.save(Account(
                id = it.id,
                accountName = accountDto.accountName,
                transactions = it.transactions
            ))
            Account(
                id = save.id,
                accountName = save.accountName,
                transactions = save.transactions
            )
        }.orElseGet(null)
    }

}