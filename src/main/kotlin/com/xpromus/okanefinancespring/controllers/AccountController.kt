package com.xpromus.okanefinancespring.controllers

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.entities.Account
import com.xpromus.okanefinancespring.services.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAccounts(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "accountName", required = false) accountName: String?,
    ): List<Account> {
        return accountService.getAllAccounts(id, accountName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody accountDto: AccountDto): Account {
        return accountService.createAccount(accountDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAccount(
        @PathVariable id: UUID
    ) {
        accountService.deleteAccount(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateAccount(
        @PathVariable id: UUID,
        @RequestBody accountDto: AccountDto,
    ): Account {
        return accountService.updateAccount(accountDto, id)
    }

    @PutMapping("/{id}/transactions")
    @ResponseStatus(HttpStatus.OK)
    fun addTransactions(
        @PathVariable id: UUID,
        @RequestBody transactions: List<UUID>,
    ): Account {
        return accountService.addTransactions(transactions, id)
    }

}