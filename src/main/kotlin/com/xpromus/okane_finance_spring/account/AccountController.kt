package com.xpromus.okane_finance_spring.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController @Autowired constructor(
    private val accountService: AccountService
) {

    @GetMapping
    fun getAccounts(): ResponseEntity<List<Account>> {
        return ResponseEntity<List<Account>>(
            accountService.getAllAccounts(),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun createAccount(@RequestBody account: AccountDto): ResponseEntity<Account> {
        return ResponseEntity<Account>(
            accountService.createAccount(account),
            HttpStatus.OK
        )
    }

}