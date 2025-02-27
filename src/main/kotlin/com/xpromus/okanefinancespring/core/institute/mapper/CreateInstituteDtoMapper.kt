package com.xpromus.okanefinancespring.core.institute.mapper

import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.institute.dtos.CreateInstituteDto

fun fromCreateInstituteDto(createInstituteDto: CreateInstituteDto): Institute {
    return Institute(
        instituteName = createInstituteDto.instituteName
    )
}
