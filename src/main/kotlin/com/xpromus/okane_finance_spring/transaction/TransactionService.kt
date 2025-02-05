package com.xpromus.okane_finance_spring.transaction

import com.xpromus.okane_finance_spring.account.AccountService
import com.xpromus.okane_finance_spring.payee.PayeeService
import com.xpromus.okane_finance_spring.transaction.category.CategoryService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService @Autowired constructor(
    private val transactionRepository: TransactionRepository,
    private val accountService: AccountService,
    private val payeeService: PayeeService,
    private val categoryService: CategoryService
) {

    fun getTransactionById(id: UUID): Transaction? {
        return transactionRepository.findTransactionById(id)
    }

    fun getAllTransactions(id: UUID?, transactionName: String?): List<Transaction> {
        if ((id ?: transactionName) != null) {
            return transactionRepository.findTransactionByIdAndTransactionName(id, transactionName)
        }

        return transactionRepository.findAll()
    }

    fun createTransaction(transactionDto: TransactionDto): Transaction {
        return transactionRepository.save(
            covertTransactionDtoToTransaction(
                transactionDto,
                accountService,
                payeeService,
                categoryService
            )
        )
    }

    @Transactional
    fun deleteTransaction(transaction: Transaction) {
        transactionRepository.delete(transaction)
    }

    fun updateTransaction(transactionDto: TransactionDto, id: UUID): Transaction {
        val updatedTransaction = covertTransactionDtoToTransaction(
            transactionDto,
            accountService,
            payeeService,
            categoryService
        )
        return transactionRepository.findById(id).map {
            val save = transactionRepository.save(Transaction(
                id = it.id,
                transactionName = updatedTransaction.transactionName,
                doneDate = updatedTransaction.doneDate,
                finishedDate = updatedTransaction.finishedDate,
                amount = updatedTransaction.amount,
                targetAccount = updatedTransaction.targetAccount,
                targetPayee = updatedTransaction.targetPayee,
                targetCategory = updatedTransaction.targetCategory
            ))
            Transaction(
                id = save.id,
                transactionName = save.transactionName,
                doneDate = save.doneDate,
                finishedDate = save.finishedDate,
                amount = save.amount,
                targetAccount = save.targetAccount,
                targetPayee = save.targetPayee,
                targetCategory = save.targetCategory,
            )
        }.orElseGet(null)
    }

}