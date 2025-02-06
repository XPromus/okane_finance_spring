package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Owner
import java.util.*

data class OwnerDto(
    val firstName: String,
    val lastName: String,
    val birthday: Date,
)

fun convertOwnerDtoToOwner(ownerDto: OwnerDto): Owner {
    return Owner(
        id = UUID.randomUUID(),
        firstName = ownerDto.firstName,
        lastName = ownerDto.lastName,
        birthday = ownerDto.birthday
    )
}