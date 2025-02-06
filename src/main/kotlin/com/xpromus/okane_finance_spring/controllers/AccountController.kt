package com.xpromus.okane_finance_spring.controllers

import com.xpromus.okane_finance_spring.dto.AccountDto
import com.xpromus.okane_finance_spring.services.AccountService
import com.xpromus.okane_finance_spring.entities.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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