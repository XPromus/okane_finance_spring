package com.xpromus.okanefinancespring.core.payees.mapper

import com.xpromus.okanefinancespring.core.payees.Payee
import com.xpromus.okanefinancespring.core.payees.dtos.GetPayeeDto

fun toGetPayeeDto(payee: Payee): GetPayeeDto {
    return GetPayeeDto(
        id = payee.id!!,
        payeeName = payee.payeeName,
        transactionIDs = payee.transactions.map { it.id!! }
    )
}
