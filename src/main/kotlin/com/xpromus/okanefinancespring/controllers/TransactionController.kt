package com.xpromus.okanefinancespring.controllers

import com.xpromus.okanefinancespring.entities.Transaction
import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.services.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTransaction(
        @RequestParam(name = "id", required = true) id: UUID
    ) {
        transactionService.deleteTransaction(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateTransaction(
        @RequestBody transactionDto: TransactionDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): Transaction {
        return transactionService.updateTransaction(transactionDto, id)
    }

}