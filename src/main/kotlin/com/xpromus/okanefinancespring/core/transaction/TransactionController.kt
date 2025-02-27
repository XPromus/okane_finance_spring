package com.xpromus.okanefinancespring.core.transaction

import com.xpromus.okanefinancespring.core.transaction.dtos.CreateTransactionDto
import com.xpromus.okanefinancespring.core.transaction.dtos.EditTransactionDto
import com.xpromus.okanefinancespring.core.transaction.dtos.GetTransactionDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/transactions")
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTransactions(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "transactionName", required = false) transactionName: String?,
        @RequestParam(name = "doneDate", required = false) doneDate: Date?,
        @RequestParam(name = "finishedDate", required = false) finishedDate: Date?,
        @RequestParam(name = "amount", required = false) amount: Long?,
     ): List<GetTransactionDto> {
        return transactionService.getAllTransactions(
            id,
            transactionName,
            doneDate,
            finishedDate,
            amount
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTransaction(
        @RequestBody createTransactionDto: CreateTransactionDto
    ): GetTransactionDto {
        return transactionService.createTransaction(createTransactionDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTransaction(
        @PathVariable id: UUID,
        @RequestBody editTransactionDto: EditTransactionDto
    ): GetTransactionDto {
        return transactionService.updateTransaction(id, editTransactionDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTransaction(
        @PathVariable id: UUID
    ) {
        transactionService.deleteTransaction(id)
    }
}