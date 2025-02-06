package com.xpromus.okane_finance_spring.dto

import com.xpromus.okane_finance_spring.entities.Payee
import java.util.*

data class PayeeDto(
    val payeeName: String
)

fun convertPayeeDtoToPayee(payeeDto: PayeeDto): Payee {
    return Payee(
        id = UUID.randomUUID(),
        payeeName = payeeDto.payeeName
    )
}