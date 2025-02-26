package com.xpromus.okanefinancespring

import com.xpromus.okanefinancespring.accounts.AccountDto
import com.xpromus.okanefinancespring.owners.OwnerDto
import com.xpromus.okanefinancespring.core.payees.PayeeDto
import com.xpromus.okanefinancespring.core.transactions.transaction.TransactionDto
import com.xpromus.okanefinancespring.accounts.AccountService
import com.xpromus.okanefinancespring.core.owners.OwnerService
import com.xpromus.okanefinancespring.payees.PayeeService
import com.xpromus.okanefinancespring.transactions.transaction.TransactionService
import kotlin.test.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class OverallTest @Autowired constructor (
    val ownerService: com.xpromus.okanefinancespring.core.owners.OwnerService,
    val accountService: AccountService,
    val payeeService: PayeeService,
    val transactionService: TransactionService
) {

    @Test
    fun createAll() {
        val ownerDto = OwnerDto(
            firstName = "John",
            lastName = "Doe",
            birthday = Date()
        )
        val ownerRequest = ownerService.createOwner(ownerDto)

        val accountDto = AccountDto(
            accountName = "Sparkasse",
            ownerId = ownerRequest.id.toString()
        )
        val accountRequest = accountService.createAccount(accountDto)

        val payeeDto = com.xpromus.okanefinancespring.core.payees.PayeeDto(
            payeeName = "Dieter"
        )
        val payeeRequest = payeeService.createPayee(payeeDto)

        val transactionDto = com.xpromus.okanefinancespring.core.transactions.transaction.TransactionDto(
            transactionName = "Auto",
            doneDate = Date(),
            finishedDate = Date(),
            amount = 50000,
            accountId = accountRequest.id.toString(),
            payeeId = payeeRequest.id.toString(),
            categoryId = null,
            tagIds = emptyList()
        )
        val transactionRequest = transactionService.createTransaction(transactionDto)
    }

}