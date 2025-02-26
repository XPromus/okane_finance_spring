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

fun convertOwnerToOwnerDto(owner: Owner): OwnerDto {
    return OwnerDto(
        id = owner.id.toString(),
        firstName = owner.firstName,
        lastName = owner.lastName,
        birthday = owner.birthday,
        accountIDs = owner.accounts.map { it.id.toString() },
        depotIDs = owner.depots.map { it.id.toString() }
    )
}