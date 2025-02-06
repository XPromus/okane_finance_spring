package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.dto.convertAccountDtoToAccount
import com.xpromus.okanefinancespring.entities.Account
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.repositories.AccountRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService @Autowired constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountById(id: UUID): Account {
        return accountRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Account with id $id could not be found")
        }
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
    fun deleteAccount(id: UUID) {
        val toDeleteAccount = getAccountById(id)
        accountRepository.delete(toDeleteAccount)
    }

    fun updateAccount(accountDto: AccountDto, id: UUID): Account {
        return accountRepository.findById(id).map {
            val save = accountRepository.save(
                Account(
                    id = it.id,
                    accountName = accountDto.accountName,
                    transactions = it.transactions
                )
            )
            Account(
                id = save.id,
                accountName = save.accountName,
                transactions = save.transactions
            )
        }.orElseGet(null)
    }

}