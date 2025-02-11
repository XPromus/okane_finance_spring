package com.xpromus.okanefinancespring.owners

fun convertOwnerDtoToOwner(
    ownerDto: OwnerDto
): Owner {
    return Owner(
        firstName = ownerDto.firstName,
        lastName = ownerDto.lastName,
        birthday = ownerDto.birthday
    )
}
