package com.xpromus.okanefinancespring.core.transaction

import com.xpromus.okanefinancespring.core.accounts.AccountService
import com.xpromus.okanefinancespring.core.payees.PayeeService
import com.xpromus.okanefinancespring.core.categories.Category
import com.xpromus.okanefinancespring.core.categories.CategoryService
import com.xpromus.okanefinancespring.core.transaction.dtos.CreateTransactionDto
import com.xpromus.okanefinancespring.core.transaction.dtos.EditTransactionDto
import com.xpromus.okanefinancespring.core.transaction.dtos.GetTransactionDto
import com.xpromus.okanefinancespring.core.transaction.mapper.fromCreateTransactionDto
import com.xpromus.okanefinancespring.core.transaction.mapper.fromEditTransactionDto
import com.xpromus.okanefinancespring.core.transaction.mapper.toGetTransactionDto
import com.xpromus.okanefinancespring.core.transaction.mapper.toTransactionClassesDto
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountService: AccountService,
    private val payeeService: PayeeService,
    private val categoryService: CategoryService
) {
    fun getTransactionById(id: UUID): Transaction {
        return transactionRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Transaction", id.toString())
            )
        }
    }

    fun getAllTransactions(
        id: UUID?,
        transactionName: String?,
        doneDate: Date?,
        finishedDate: Date?,
        amount: Long?,
    ): List<GetTransactionDto> {
        val transactionsToReturn = transactionRepository.findTransactionsByFields(
            id,
            transactionName,
            doneDate,
            finishedDate,
            amount
        )
        return transactionsToReturn.map { toGetTransactionDto(it) }
    }

    fun createTransaction(createTransactionDto: CreateTransactionDto): GetTransactionDto {
        val transactionClasses = toTransactionClassesDto(
            createTransactionDto.targetAccountID,
            createTransactionDto.targetPayeeID,
            createTransactionDto.targetCategoryID,
            accountService, payeeService, categoryService
        )

        val newTransaction = transactionRepository.save(
            fromCreateTransactionDto(transactionClasses, createTransactionDto)
        )
        return toGetTransactionDto(newTransaction)
    }

    fun updateTransaction(id: UUID, editTransactionDto: EditTransactionDto): GetTransactionDto {
        val targetAccount = if (editTransactionDto.targetAccountID == null) {
            null
        } else {
            accountService.getAccountById(editTransactionDto.targetAccountID)
        }
        val targetPayee = if (editTransactionDto.targetPayeeID == null) {
            null
        } else {
            payeeService.getPayeeById(editTransactionDto.targetPayeeID)
        }
        val targetCategory: Category? = if (editTransactionDto.targetCategoryID == null) {
            null
        } else {
            categoryService.getCategoryById(editTransactionDto.targetCategoryID)
        }

        return transactionRepository.findById(id).map {
            val save = transactionRepository.save(
                fromEditTransactionDto(
                    it,
                    targetAccount,
                    targetPayee,
                    targetCategory,
                    editTransactionDto
                )
            )
            toGetTransactionDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteTransaction(id: UUID) {
        val toDeleteTransaction = getTransactionById(id)
        transactionRepository.delete(toDeleteTransaction)
    }
}