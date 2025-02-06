package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.PayeeDto
import com.xpromus.okanefinancespring.entities.Payee

fun convertPayeeDtoToPayee(
    payeeDto: PayeeDto
): Payee {
    return Payee(
        payeeName = payeeDto.payeeName
    )
}
