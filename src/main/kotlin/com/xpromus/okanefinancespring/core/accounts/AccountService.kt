package com.xpromus.okanefinancespring.core.accounts

import com.xpromus.okanefinancespring.core.accounts.dtos.CreateAccountDto
import com.xpromus.okanefinancespring.core.accounts.dtos.EditAccountDto
import com.xpromus.okanefinancespring.core.accounts.dtos.GetAccountDto
import com.xpromus.okanefinancespring.core.accounts.mapper.*
import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.institute.InstituteService
import com.xpromus.okanefinancespring.core.owners.Owner
import com.xpromus.okanefinancespring.core.owners.OwnerService
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val ownerService: OwnerService,
    private val instituteService: InstituteService
) {

    fun getAccountById(id: UUID): Account {
        return accountRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Account with id $id could not be found")
        }
    }

    fun getAllAccounts(
        id: UUID?,
        accountName: String?,
        startingBalance: Long?,
        instituteID: UUID?,
        ownerID: UUID?
    ): List<GetAccountDto> {
        val accountsToReturn: List<Account> = accountRepository.findBudgetsByFields(
            id, accountName, startingBalance, instituteID, ownerID
        )
        return accountsToReturn.map { toGetAccountDto(it) }
    }

    fun createAccount(createAccountDto: CreateAccountDto): GetAccountDto {
        val accountInstitute = instituteService.getInstituteById(createAccountDto.instituteID)
        val accountOwner = ownerService.getOwnerById(createAccountDto.ownerID)
        val accountToSave = fromCreateAccountDto(createAccountDto, accountInstitute, accountOwner)
        val savedAccount = accountRepository.save(accountToSave)
        return toGetAccountDto(savedAccount)
    }

    fun updateAccount(id: UUID, editAccountDto: EditAccountDto): GetAccountDto {
        return accountRepository.findById(id).map {
            val targetInstitute: Institute = if (editAccountDto.instituteID == null) {
                it.institute
            } else {
                instituteService.getInstituteById(editAccountDto.instituteID)
            }

            val targetOwner: Owner = if (editAccountDto.ownerID == null) {
                it.owner
            } else {
                ownerService.getOwnerById(editAccountDto.ownerID)
            }

            val save = accountRepository.save(
                fromEditAccountDto(it, editAccountDto, targetInstitute, targetOwner)
            )
            toGetAccountDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteAccount(id: UUID) {
        val toDeleteAccount = getAccountById(id)
        accountRepository.delete(toDeleteAccount)
    }

}