package com.xpromus.okanefinancespring.transactions.recurring

fun convertRecurringTransactionDtoToRecurringTransaction(
    recurringTransactionDto: RecurringTransactionDto,
    recurringTransactionClasses: RecurringTransactionClasses
): RecurringTransaction {
    return RecurringTransaction(
        transactionName = recurringTransactionDto.transactionName,
        dayOfTheMonth = recurringTransactionDto.dayOfTheMonth,
        monthInterval = recurringTransactionDto.monthInterval,
        dayOfTheWeek = recurringTransactionDto.dayOfTheWeek,
        weekInterval = recurringTransactionDto.weekInterval,
        recurringUntil = recurringTransactionDto.recurringUntil,
        amount = recurringTransactionDto.amount,
        targetAccount = recurringTransactionClasses.account,
        targetPayee = recurringTransactionClasses.payee,
        targetCategory = recurringTransactionClasses.category,
        targetTags = recurringTransactionClasses.tags
    )
}