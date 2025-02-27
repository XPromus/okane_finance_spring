package com.xpromus.okanefinancespring.core.owners.dtos

import java.util.Date

data class EditOwnerDto(
    val firstName: String?,
    val lastName: String?,
    val birthday: Date?
)
