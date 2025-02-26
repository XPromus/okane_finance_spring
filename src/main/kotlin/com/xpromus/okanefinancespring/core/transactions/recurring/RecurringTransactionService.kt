package com.xpromus.okanefinancespring.core.transactions.recurring

import com.xpromus.okanefinancespring.core.accounts.AccountService
import com.xpromus.okanefinancespring.core.payees.PayeeService
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.core.sorting.categories.Category
import com.xpromus.okanefinancespring.core.sorting.categories.CategoryService
import com.xpromus.okanefinancespring.core.sorting.tags.Tag
import com.xpromus.okanefinancespring.core.sorting.tags.TagService
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecurringTransactionService(
    val recurringTransactionRepository: RecurringTransactionRepository,
    val accountService: AccountService,
    val payeeService: PayeeService,
    val categoryService: CategoryService,
    val tagService: TagService,
) {

    fun getRecurringTransactionById(id: UUID): RecurringTransaction {
        return recurringTransactionRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Recurring transaction", id.toString())
            )
        }
    }

    fun getAllRecurringTransactions(
        id: UUID?,
        transactionName: String?,
        amount: Long?,
        dayOfTheMonth: Int?,
        monthInterval: Int?,
        dayOfTheWeek: Int?,
        weekInterval: Int?,
        recurringUntil: Date?,
    ): List<RecurringTransaction> {
        return recurringTransactionRepository.findRecurringTransactionsByFields(
            id,
            transactionName,
            amount,
            dayOfTheMonth,
            monthInterval,
            dayOfTheWeek,
            weekInterval,
            recurringUntil
        )
    }

    fun createRecurringTransaction(
        recurringTransactionDto: RecurringTransactionDto,
    ): RecurringTransaction {
        val recurringTransactionClasses = getRecurringTransactionClasses(
            recurringTransactionDto.accountId,
            recurringTransactionDto.payeeId,
            recurringTransactionDto.categoryId,
            recurringTransactionDto.tagIds
        )

        val recurringTransactionToBeAdded = convertRecurringTransactionDtoToRecurringTransaction(
            recurringTransactionDto, recurringTransactionClasses
        )
        return recurringTransactionRepository.save(recurringTransactionToBeAdded)
    }

    @Transactional
    fun deleteRecurringTransaction(id: UUID) {
        val toDeleteRecurringTransaction = getRecurringTransactionById(id)
        recurringTransactionRepository.delete(toDeleteRecurringTransaction)
    }

    fun updateRecurringTransaction(
        recurringTransactionDto: RecurringTransactionDto,
        id: UUID,
    ): RecurringTransaction {
        val recurringTransactionClasses = getRecurringTransactionClasses(
            recurringTransactionDto.accountId,
            recurringTransactionDto.payeeId,
            recurringTransactionDto.categoryId,
            recurringTransactionDto.tagIds
        )

        val updatedRecurringTransaction = convertRecurringTransactionDtoToRecurringTransaction(
            recurringTransactionDto, recurringTransactionClasses
        )

        return recurringTransactionRepository.findById(id).map {
            val save = recurringTransactionRepository.save(
                RecurringTransaction(
                    id = it.id,
                    transactionName = updatedRecurringTransaction.transactionName,
                    amount = updatedRecurringTransaction.amount,
                    dayOfTheMonth = updatedRecurringTransaction.dayOfTheMonth,
                    monthInterval = updatedRecurringTransaction.monthInterval,
                    dayOfTheWeek = updatedRecurringTransaction.dayOfTheWeek,
                    weekInterval = updatedRecurringTransaction.weekInterval,
                    recurringUntil = updatedRecurringTransaction.recurringUntil,
                    targetAccount = updatedRecurringTransaction.targetAccount,
                    targetPayee = updatedRecurringTransaction.targetPayee,
                    targetCategory = updatedRecurringTransaction.targetCategory,
                    targetTags = updatedRecurringTransaction.targetTags
                )
            )
            RecurringTransaction(
                id = save.id,
                transactionName = save.transactionName,
                amount = save.amount,
                dayOfTheMonth = save.dayOfTheMonth,
                monthInterval = save.monthInterval,
                dayOfTheWeek = save.dayOfTheWeek,
                weekInterval = save.weekInterval,
                recurringUntil = save.recurringUntil,
                targetAccount = save.targetAccount,
                targetPayee = save.targetPayee,
                targetCategory = save.targetCategory,
                targetTags = save.targetTags
            )
        }.orElseGet(null)
    }

    fun getRecurringTransactionClasses(
        accountId: String,
        payeeId: String,
        categoryId: String?,
        tagIds: List<String>,
    ): RecurringTransactionClasses {
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

        return RecurringTransactionClasses(
            account = targetAccount,
            payee = targetPayee,
            category = targetCategory,
            tags = targetTags
        )
    }
}