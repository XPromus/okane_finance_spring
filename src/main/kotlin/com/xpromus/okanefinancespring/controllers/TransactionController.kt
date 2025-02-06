package com.xpromus.okanefinancespring.controllers

import com.xpromus.okanefinancespring.entities.Transaction
import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.services.TransactionService
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
        @RequestParam(name = "amount", required = false) amount: Long?
    ): List<Transaction> {
        return transactionService.getAllTransactions(id, transactionName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTransaction(
        @RequestBody transactionDto: TransactionDto
    ): Transaction {
        return transactionService.createTransaction(transactionDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTransaction(
        @PathVariable id: UUID
    ) {
        transactionService.deleteTransaction(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTransaction(
        @PathVariable id: UUID,
        @RequestBody transactionDto: TransactionDto
    ): Transaction {
        return transactionService.updateTransaction(transactionDto, id)
    }

}