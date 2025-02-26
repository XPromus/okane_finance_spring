package com.xpromus.okanefinancespring.core.payees

fun convertPayeeDtoToPayee(
    payeeDto: PayeeDto
): Payee {
    return Payee(
        payeeName = payeeDto.payeeName
    )
}
