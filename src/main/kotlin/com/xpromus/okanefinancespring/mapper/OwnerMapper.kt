package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.OwnerDto
import com.xpromus.okanefinancespring.entities.Owner

fun convertOwnerDtoToOwner(
    ownerDto: OwnerDto
): Owner {
    return Owner(
        firstName = ownerDto.firstName,
        lastName = ownerDto.lastName,
        birthday = ownerDto.birthday
    )
}
