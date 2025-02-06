package com.xpromus.okanefinancespring.controllers

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.services.AccountService
import com.xpromus.okanefinancespring.entities.Account
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAccounts(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "accountName", required = false) accountName: String?
    ): List<Account> {
        return accountService.getAllAccounts(id, accountName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody accountDto: AccountDto): Account {
        return accountService.createAccount(accountDto)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAccount(
        @RequestParam(name = "id", required = true) id: UUID
    ) {
        accountService.deleteAccount(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateAccount(
        @RequestBody accountDto: AccountDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): Account {
        return accountService.updateAccount(accountDto, id)
    }

}