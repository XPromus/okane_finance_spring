package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.Transaction
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.mapper.covertTransactionDtoToTransaction
import com.xpromus.okanefinancespring.repositories.TransactionRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService @Autowired constructor(
    private val transactionRepository: TransactionRepository
) {

    fun getTransactionById(id: UUID): Transaction {
        return transactionRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Transaction with id $id could not be found")
        }
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
                transactionDto
            )
        )
    }

    @Transactional
    fun deleteTransaction(id: UUID) {
        val toDeleteTransaction = getTransactionById(id)
        transactionRepository.delete(toDeleteTransaction)
    }

    fun updateTransaction(transactionDto: TransactionDto, id: UUID): Transaction {
        val updatedTransaction = covertTransactionDtoToTransaction(
            transactionDto
        )
        return transactionRepository.findById(id).map {
            val save = transactionRepository.save(
                Transaction(
                    id = it.id,
                    transactionName = updatedTransaction.transactionName,
                    doneDate = updatedTransaction.doneDate,
                    finishedDate = updatedTransaction.finishedDate,
                    amount = updatedTransaction.amount,
                    targetAccount = it.targetAccount,
                    targetPayee = it.targetPayee,
                    targetCategory = it.targetCategory
                )
            )
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