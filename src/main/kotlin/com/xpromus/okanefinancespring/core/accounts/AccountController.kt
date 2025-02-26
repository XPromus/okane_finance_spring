package com.xpromus.okanefinancespring.core.accounts

import com.xpromus.okanefinancespring.core.accounts.dtos.CreateAccountDto
import com.xpromus.okanefinancespring.core.accounts.dtos.EditAccountDto
import com.xpromus.okanefinancespring.core.accounts.dtos.GetAccountDto
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
        @RequestParam(name = "instituteID", required = false) instituteID: UUID?,
        @RequestParam(name = "ownerID", required = false) ownerID: UUID?
    ): List<GetAccountDto> {
        return accountService.getAllAccounts(
            id,
            accountName,
            startingBalance,
            instituteID,
            ownerID
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody accountDto: CreateAccountDto): GetAccountDto {
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
        @RequestBody accountDto: EditAccountDto,
    ): GetAccountDto {
        return accountService.updateAccount(id, accountDto)
    }

}