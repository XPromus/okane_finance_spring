package com.xpromus.okanefinancespring.accounts

import com.xpromus.okanefinancespring.calculations.getExpensesFromTransactions
import com.xpromus.okanefinancespring.calculations.getIncomeFromTransactions
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.institute.InstituteService
import com.xpromus.okanefinancespring.owners.OwnerService
import com.xpromus.okanefinancespring.transactions.transaction.TransactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val ownerService: OwnerService,
    private val instituteService: InstituteService
) {

    fun getAccountById(id: UUID): Account {
        return accountRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Account with id $id could not be found")
        }
    }

    fun getIncomeOfAccountInDateRange(id: UUID, lowerRange: Date?, upperRange: Date?): Long {
        val targetAccount = getAccountById(id)
        val transactions = transactionRepository.findTransactionsByFinishedDateRange(
            targetAccount,
            lowerRange,
            upperRange
        )
        return getIncomeFromTransactions(transactions)
    }

    fun getExpensesOfAccountInDateRange(id: UUID, lowerRange: Date?, upperRange: Date?): Long {
        val targetAccount = getAccountById(id)
        val transactions = transactionRepository.findTransactionsByFinishedDateRange(
            targetAccount,
            lowerRange,
            upperRange
        )
        return getExpensesFromTransactions(transactions)
    }

    fun getAllAccounts(
        id: UUID?,
        accountName: String?,
        startingBalance: Long?
    ): List<AccountDto> {
        val accountsToReturn: List<Account> = accountRepository.findBudgetsByFields(id, accountName, startingBalance)
        return accountsToReturn.map { convertAccountToAccountDto(it) }
    }

    fun createAccount(accountDto: AccountDto): UUID {
        val accountInstitute = instituteService.getInstituteById(UUID.fromString(accountDto.instituteId))
        val accountOwner = ownerService.getOwnerById(UUID.fromString(accountDto.ownerId))
        val accountToSave = convertAccountDtoToAccount(accountDto, accountInstitute, accountOwner)
        val savedAccount = accountRepository.save(accountToSave)
        return savedAccount.id!!
    }

    @Transactional
    fun deleteAccount(id: UUID) {
        val toDeleteAccount = getAccountById(id)
        accountRepository.delete(toDeleteAccount)
    }

    fun updateAccount(accountDto: AccountDto, id: UUID): AccountDto {
        return accountRepository.findById(id).map {
            val save = accountRepository.save(
                Account(
                    id = it.id,
                    accountName = accountDto.accountName,
                    startingBalance = accountDto.startingBalance,
                    institute = instituteService.getInstituteById(UUID.fromString(accountDto.instituteId)),
                    transactions = it.transactions,
                    owner = ownerService.getOwnerById(UUID.fromString(accountDto.ownerId))
                )
            )
            convertAccountToAccountDto(save)
        }.orElseGet(null)
    }

}