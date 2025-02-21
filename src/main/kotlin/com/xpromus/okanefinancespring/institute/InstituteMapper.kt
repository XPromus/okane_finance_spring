package com.xpromus.okanefinancespring.institute

fun convertInstituteDtoToInstitute(
    instituteDto: InstituteDto
): Institute {
    return Institute(
        name = instituteDto.name
    )
}