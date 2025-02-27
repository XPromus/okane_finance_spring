package com.xpromus.okanefinancespring.core.payees.mapper

import com.xpromus.okanefinancespring.core.payees.Payee
import com.xpromus.okanefinancespring.core.payees.dtos.CreatePayeeDto

fun fromCreatePayeeDto(createPayeeDto: CreatePayeeDto): Payee {
    return Payee(
        payeeName = createPayeeDto.payeeName
    )
}