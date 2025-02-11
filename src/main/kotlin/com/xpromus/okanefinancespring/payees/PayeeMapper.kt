package com.xpromus.okanefinancespring.payees

fun convertPayeeDtoToPayee(
    payeeDto: PayeeDto
): Payee {
    return Payee(
        payeeName = payeeDto.payeeName
    )
}
