package com.xpromus.okanefinancespring.core.payees.mapper

import com.xpromus.okanefinancespring.core.payees.Payee
import com.xpromus.okanefinancespring.core.payees.dtos.EditPayeeDto

fun fromEditPayeeDto(payee: Payee, editPayeeDto: EditPayeeDto): Payee {
    return Payee(
        id = payee.id,
        payeeName = editPayeeDto.payeeName ?: payee.payeeName,
        transactions = payee.transactions
    )
}