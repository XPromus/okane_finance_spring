package com.xpromus.okanefinancespring

import com.xpromus.okanefinancespring.dto.AccountDto
import com.xpromus.okanefinancespring.dto.OwnerDto
import com.xpromus.okanefinancespring.dto.PayeeDto
import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.Transaction
import com.xpromus.okanefinancespring.mapper.convertOwnerDtoToOwner
import com.xpromus.okanefinancespring.repositories.TagRepository
import com.xpromus.okanefinancespring.services.AccountService
import com.xpromus.okanefinancespring.services.OwnerService
import com.xpromus.okanefinancespring.services.PayeeService
import com.xpromus.okanefinancespring.services.TransactionService
import kotlin.test.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class OverallTest @Autowired constructor (
    val ownerService: OwnerService,
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

        val payeeDto = PayeeDto(
            payeeName = "Dieter"
        )
        val payeeRequest = payeeService.createPayee(payeeDto)

        val transactionDto = TransactionDto(
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