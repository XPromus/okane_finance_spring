package com.xpromus.okanefinancespring.core.institute

import com.xpromus.okanefinancespring.core.institute.dtos.CreateInstituteDto
import com.xpromus.okanefinancespring.core.institute.dtos.EditInstituteDto
import com.xpromus.okanefinancespring.core.institute.dtos.GetInstituteDto
import com.xpromus.okanefinancespring.core.institute.mapper.fromCreateInstituteDto
import com.xpromus.okanefinancespring.core.institute.mapper.fromEditInstituteDto
import com.xpromus.okanefinancespring.core.institute.mapper.toGetInstituteDto
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InstituteService (
    private val instituteRepository: InstituteRepository
) {
    fun getInstituteById(id: UUID): Institute {
        return instituteRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Institute", id.toString())
            )
        }
    }

    fun getAllInstitutes(
        id: UUID?,
        name: String?
    ): List<GetInstituteDto> {
        val institutesToReturn = instituteRepository.findInstitutesByFields(
            id, name
        )
        return institutesToReturn.map { toGetInstituteDto(it) }
    }

    fun createInstitute(createInstituteDto: CreateInstituteDto): GetInstituteDto {
        val newInstitute = instituteRepository.save(fromCreateInstituteDto(createInstituteDto))
        return toGetInstituteDto(newInstitute)
    }

    fun updateInstitute(id: UUID, editInstituteDto: EditInstituteDto): GetInstituteDto {
        return instituteRepository.findById(id).map {
            val save = instituteRepository.save(
                fromEditInstituteDto(it, editInstituteDto)
            )
            toGetInstituteDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteInstitute(id: UUID) {
        val toDeleteInstitute = getInstituteById(id)
        instituteRepository.delete(toDeleteInstitute)
    }
}