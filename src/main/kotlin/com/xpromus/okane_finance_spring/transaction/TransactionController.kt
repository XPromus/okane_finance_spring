package com.xpromus.okane_finance_spring.transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/transactions")
class TransactionController @Autowired constructor(
    private val transactionService: TransactionService
) {

    @GetMapping
    fun getTransactions(
        @RequestParam(name = "id", required = false) id: UUID,
        @RequestParam(name = "transactionName", required = false) transactionName: String,
        @RequestParam(name = "doneDate", required = false) doneDate: Date,
        @RequestParam(name = "finishedDate", required = false) finishedDate: Date,
        @RequestParam(name = "amount", required = false) amount: Long
    ) : ResponseEntity<List<Transaction>> {
        return ResponseEntity<List<Transaction>>(
            transactionService.getAllTransactions(id, transactionName),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun createTransaction(
        @RequestBody transactionDto: TransactionDto
    ) : ResponseEntity<Transaction> {
        return ResponseEntity<Transaction>(
            transactionService.createTransaction(transactionDto),
            HttpStatus.OK
        )
    }

    @DeleteMapping
    fun deleteTransaction(
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<String> {
        val toDeleteTransaction = transactionService.getTransactionById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        transactionService.deleteTransaction(toDeleteTransaction)
        return ResponseEntity<String>(
            "Transaction with id: $id has been deleted",
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updateTransaction(
        @RequestBody transactionDto: TransactionDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<Transaction> {
        return ResponseEntity<Transaction>(
            transactionService.updateTransaction(transactionDto, id),
            HttpStatus.OK
        )
    }

}