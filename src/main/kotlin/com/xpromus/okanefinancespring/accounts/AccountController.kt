package com.xpromus.okanefinancespring.accounts

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
        @RequestParam(name = "startingBalance", required = false) startingBalance: Long?,
        @RequestParam(name = "institute", required = false) institute: String?
    ): List<Account> {
        return accountService.getAllAccounts(id, accountName, startingBalance, institute)
    }

    @GetMapping("/{id}/expenses")
    @ResponseStatus(HttpStatus.OK)
    fun getAccountExpensesInDateRange(
        @PathVariable id: UUID,
        @RequestParam(name = "lowerDate", required = false) lowerDate: Date?,
        @RequestParam(name = "upperDate", required = false) upperDate: Date?
    ): Long {
        return accountService.getExpensesOfAccountInDateRange(id, lowerDate, upperDate)
    }

    @GetMapping("/{id}/income")
    fun getAccountIncomeInDateRange(
        @PathVariable id: UUID,
        @RequestParam(name = "lowerDate", required = false) lowerDate: Date?,
        @RequestParam(name = "upperDate", required = false) upperDate: Date?
    ): Long {
        return accountService.getIncomeOfAccountInDateRange(id, lowerDate, upperDate)
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

}