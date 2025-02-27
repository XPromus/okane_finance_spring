package com.xpromus.okanefinancespring.core.institute.mapper

import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.institute.dtos.GetInstituteDto

fun toGetInstituteDto(institute: Institute): GetInstituteDto {
    return GetInstituteDto(
        id = institute.id!!,
        instituteName = institute.instituteName,
        accountIDs = institute.accounts.map { it.id!! },
        depotIDs = institute.depots.map { it.id!! }
    )
}