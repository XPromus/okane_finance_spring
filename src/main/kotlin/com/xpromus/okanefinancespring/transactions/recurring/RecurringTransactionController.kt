package com.xpromus.okanefinancespring.transactions.recurring

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/recurring-transaction")
class RecurringTransactionController(
    private val recurringTransactionService: RecurringTransactionService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getRecurringTransactions(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "transactionName", required = false) transactionName: String?,
        @RequestParam(name = "amount", required = false) amount: Long?,
        @RequestParam(name = "dayOfTheMonth", required = false) dayOfTheMonth: Int?,
        @RequestParam(name = "monthInterval", required = false) monthInterval: Int?,
        @RequestParam(name = "dayOfTheWeek", required = false) dayOfTheWeek: Int?,
        @RequestParam(name = "weekInterval", required = false) weekInterval: Int?,
        @RequestParam(name = "recurringUntil", required = false) recurringUntil: Date?,
    ): List<RecurringTransaction> {
        return recurringTransactionService.getAllRecurringTransactions(
            id, transactionName, amount, dayOfTheMonth, monthInterval, dayOfTheWeek, weekInterval, recurringUntil
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecurringTransaction(
        @RequestBody recurringTransactionDto: RecurringTransactionDto,
    ): RecurringTransaction {
        return recurringTransactionService.createRecurringTransaction(recurringTransactionDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTransaction(
        @PathVariable id: UUID,
    ) {
        recurringTransactionService.deleteRecurringTransaction(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTransaction(
        @PathVariable id: UUID,
        @RequestBody recurringTransactionDto: RecurringTransactionDto,
    ): RecurringTransaction {
        return recurringTransactionService.updateRecurringTransaction(
            recurringTransactionDto,
            id
        )
    }

}