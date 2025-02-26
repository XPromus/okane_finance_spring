package com.xpromus.okanefinancespring.core.institute

fun convertInstituteDtoToInstitute(
    instituteDto: InstituteDto
): Institute {
    return Institute(
        name = instituteDto.name
    )
}