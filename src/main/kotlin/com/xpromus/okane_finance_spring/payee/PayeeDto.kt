package com.xpromus.okane_finance_spring.payee

import java.util.*

data class PayeeDto (
    val payeeName: String
)

fun convertPayeeDtoToPayee(payeeDto: PayeeDto): Payee {
    return Payee(
        id = UUID.randomUUID(),
        payeeName = payeeDto.payeeName
    )
}