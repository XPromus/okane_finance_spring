package com.xpromus.okane_finance_spring.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/accounts")
class AccountController @Autowired constructor(
    private val accountService: AccountService
) {

    @GetMapping
    fun getAccounts(
        @RequestParam(name = "id", required = false) id: UUID,
        @RequestParam(name = "accountName", required = false) accountName: String
    ): ResponseEntity<List<Account>> {
        return ResponseEntity<List<Account>>(
            accountService.getAllAccounts(id, accountName),
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

    @DeleteMapping
    fun deleteAccount(
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<String> {
        val toDeleteAccount = accountService.getAccountById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        accountService.deleteAccount(toDeleteAccount)
        return ResponseEntity<String>(
            "Account with id: $id has been deleted",
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updateAccount(
        @RequestBody accountDto: AccountDto,
        @PathVariable id: UUID
    ): ResponseEntity<Account> {
        return ResponseEntity<Account>(
            accountService.updateAccount(accountDto, id),
            HttpStatus.OK
        )
    }

}