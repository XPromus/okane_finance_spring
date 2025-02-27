package com.xpromus.okanefinancespring.core.owners.mapper

import com.xpromus.okanefinancespring.core.owners.Owner
import com.xpromus.okanefinancespring.core.owners.dtos.EditOwnerDto

fun fromEditOwnerDto(owner: Owner, editOwnerDto: EditOwnerDto): Owner {
    return Owner(
        id = owner.id,
        firstName = editOwnerDto.firstName ?: owner.firstName,
        lastName = editOwnerDto.lastName ?: owner.lastName,
        birthday = editOwnerDto.birthday ?: owner.birthday,
        accounts = owner.accounts,
        depots = owner.depots
    )
}