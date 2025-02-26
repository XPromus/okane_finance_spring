package com.xpromus.okanefinancespring.core.institute

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
    ): List<Institute> {
        return instituteRepository.findInstitutesByFields(
            id, name
        )
    }

    fun createInstitute(instituteDto: InstituteDto): Institute {
        return instituteRepository.save(convertInstituteDtoToInstitute(instituteDto))
    }

    @Transactional
    fun deleteInstitute(id: UUID) {
        val toDeleteInstitute = getInstituteById(id)
        instituteRepository.delete(toDeleteInstitute)
    }

    fun updateInstitute(instituteDto: InstituteDto, id: UUID): Institute {
        return instituteRepository.findById(id).map {
            val save = instituteRepository.save(
                Institute(
                    id = it.id,
                    name = instituteDto.name,
                    accounts = it.accounts,
                    depots = it.depots
                )
            )
            Institute(
                id = save.id,
                name = save.name,
                accounts = save.accounts,
                depots = save.depots
            )
        }.orElseGet(null)
    }

}