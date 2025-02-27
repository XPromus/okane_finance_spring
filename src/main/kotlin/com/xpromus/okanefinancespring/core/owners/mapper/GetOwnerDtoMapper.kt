package com.xpromus.okanefinancespring.core.owners.mapper

import com.xpromus.okanefinancespring.core.owners.Owner
import com.xpromus.okanefinancespring.core.owners.dtos.GetOwnerDto

fun toGetOwnerDto(owner: Owner): GetOwnerDto {
    return GetOwnerDto(
        id = owner.id!!,
        firstName = owner.firstName,
        lastName = owner.lastName,
        birthday = owner.birthday,
        accountIDs = owner.accounts.map { it.id!! },
        depotIDs = owner.depots.map { it.id!! }
    )
}