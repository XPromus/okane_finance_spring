package com.xpromus.okanefinancespring.calculations

import com.xpromus.okanefinancespring.entities.Transaction

fun getExpensesFromTransactions(transactions: List<Transaction>): Long {
    return transactions.sumOf { if (it.amount < 0) it.amount else 0 }
}

fun getIncomeFromTransactions(transactions: List<Transaction>): Long {
    return transactions.sumOf { if (it.amount > 0) it.amount else 0 }
}

