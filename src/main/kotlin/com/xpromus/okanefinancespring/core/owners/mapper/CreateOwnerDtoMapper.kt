package com.xpromus.okanefinancespring.core.owners.mapper

import com.xpromus.okanefinancespring.core.owners.Owner
import com.xpromus.okanefinancespring.core.owners.dtos.CreateOwnerDto

fun fromCreateOwnerDto(createOwnerDto: CreateOwnerDto): Owner {
    return Owner(
        firstName = createOwnerDto.firstName,
        lastName = createOwnerDto.lastName,
        birthday = createOwnerDto.birthday
    )
}