package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.TransactionClasses
import com.xpromus.okanefinancespring.dto.TransactionDto
import com.xpromus.okanefinancespring.entities.Category
import com.xpromus.okanefinancespring.entities.Tag
import com.xpromus.okanefinancespring.entities.Transaction
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.mapper.covertTransactionDtoToTransaction
import com.xpromus.okanefinancespring.repositories.TransactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountService: AccountService,
    private val payeeService: PayeeService,
    private val categoryService: CategoryService,
    private val tagService: TagService
) {

    fun getTransactionById(id: UUID): Transaction {
        return transactionRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Transaction with id $id could not be found")
        }
    }

    fun getAllTransactions(
        id: UUID?,
        transactionName: String?,
        doneDate: Date?,
        finishedDate: Date?,
        amount: Long?,
        isRecurring: Boolean?,
        recurringDate: Date?
    ): List<Transaction> {
//        if ((id ?: transactionName) != null) {
//            return transactionRepository.findTransactionByIdAndTransactionName(id, transactionName)
//        }
//
//        return transactionRepository.findAll()

        return transactionRepository.findTransactionsByFields(
            id,
            transactionName,
            doneDate,
            finishedDate,
            amount,
            isRecurring,
            recurringDate
        )
    }

    fun createTransaction(transactionDto: TransactionDto): Transaction {
        val transactionClasses = getTransactionClasses(
            transactionDto.accountId,
            transactionDto.payeeId,
            transactionDto.categoryId,
            transactionDto.tagIds
        )

        val transactionToBeAdded = covertTransactionDtoToTransaction(
            transactionDto, transactionClasses
        )
        return transactionRepository.save(transactionToBeAdded)
    }

    @Transactional
    fun deleteTransaction(id: UUID) {
        val toDeleteTransaction = getTransactionById(id)
        transactionRepository.delete(toDeleteTransaction)
    }

    fun updateTransaction(transactionDto: TransactionDto, id: UUID): Transaction {
        val transactionClasses = getTransactionClasses(
            transactionDto.accountId,
            transactionDto.payeeId,
            transactionDto.categoryId,
            transactionDto.tagIds
        )

        val updatedTransaction = covertTransactionDtoToTransaction(
            transactionDto, transactionClasses
        )
        return transactionRepository.findById(id).map {
            val save = transactionRepository.save(
                Transaction(
                    id = it.id,
                    transactionName = updatedTransaction.transactionName,
                    doneDate = updatedTransaction.doneDate,
                    finishedDate = updatedTransaction.finishedDate,
                    amount = updatedTransaction.amount,
                    isRecurring = updatedTransaction.isRecurring,
                    recurringDate = updatedTransaction.recurringDate,
                    targetAccount = updatedTransaction.targetAccount,
                    targetPayee = updatedTransaction.targetPayee,
                    targetCategory = updatedTransaction.targetCategory,
                    targetTags = updatedTransaction.targetTags
                )
            )
            Transaction(
                id = save.id,
                transactionName = save.transactionName,
                doneDate = save.doneDate,
                finishedDate = save.finishedDate,
                amount = save.amount,
                isRecurring = save.isRecurring,
                recurringDate = save.recurringDate,
                targetAccount = save.targetAccount,
                targetPayee = save.targetPayee,
                targetCategory = save.targetCategory,
                targetTags = save.targetTags
            )
        }.orElseGet(null)
    }

    fun getTransactionClasses(
        accountId: String,
        payeeId: String,
        categoryId: String?,
        tagIds: List<String>
    ): TransactionClasses {
        val targetAccount = accountService.getAccountById(UUID.fromString(accountId))
        val targetPayee = payeeService.getPayeeById(UUID.fromString(payeeId))
        val targetCategory: Category? = if (categoryId == null) {
            null
        } else {
            categoryService.getCategoryById(UUID.fromString(categoryId))
        }
        val targetTags: List<Tag> = tagIds.map {
            tagService.getTagById(UUID.fromString(it))
        }

        return TransactionClasses(
            account = targetAccount,
            payee = targetPayee,
            category = targetCategory,
            tags = targetTags
        )
    }

}