package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Payee
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