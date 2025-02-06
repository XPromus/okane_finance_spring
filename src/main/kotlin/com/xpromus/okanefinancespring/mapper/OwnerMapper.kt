package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.OwnerDto
import com.xpromus.okanefinancespring.entities.Owner
import java.util.*

fun convertOwnerDtoToOwner(ownerDto: OwnerDto): Owner {
    return Owner(
        id = UUID.randomUUID(),
        firstName = ownerDto.firstName,
        lastName = ownerDto.lastName,
        birthday = ownerDto.birthday
    )
}
