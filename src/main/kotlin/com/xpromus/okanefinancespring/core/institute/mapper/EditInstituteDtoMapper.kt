package com.xpromus.okanefinancespring.core.institute.mapper

import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.institute.dtos.EditInstituteDto

fun fromEditInstituteDto(institute: Institute, editInstituteDto: EditInstituteDto): Institute {
    return Institute(
        id = institute.id,
        instituteName = editInstituteDto.instituteName ?: institute.instituteName,
        accounts = institute.accounts,
        depots = institute.depots
    )
}
