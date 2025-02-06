package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.PayeeDto
import com.xpromus.okanefinancespring.entities.Payee
import java.util.*

fun convertPayeeDtoToPayee(payeeDto: PayeeDto): Payee {
    return Payee(
        id = UUID.randomUUID(),
        payeeName = payeeDto.payeeName
    )
}
