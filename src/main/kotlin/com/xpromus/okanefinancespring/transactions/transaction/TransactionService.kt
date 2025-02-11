package com.xpromus.okanefinancespring.transactions.transaction

import com.xpromus.okanefinancespring.sorting.categories.Category
import com.xpromus.okanefinancespring.sorting.tags.Tag
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.accounts.AccountService
import com.xpromus.okanefinancespring.sorting.categories.CategoryService
import com.xpromus.okanefinancespring.payees.PayeeService
import com.xpromus.okanefinancespring.sorting.tags.TagService
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountService: AccountService,
    private val payeeService: PayeeService,
    private val categoryService: CategoryService,
    private val tagService: TagService,
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
    ): List<Transaction> {
        return transactionRepository.findTransactionsByFields(
            id,
            transactionName,
            doneDate,
            finishedDate,
            amount
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
        tagIds: List<String>,
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