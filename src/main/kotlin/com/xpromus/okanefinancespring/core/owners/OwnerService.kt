package com.xpromus.okanefinancespring.core.owners

import com.xpromus.okanefinancespring.core.owners.dtos.CreateOwnerDto
import com.xpromus.okanefinancespring.core.owners.dtos.EditOwnerDto
import com.xpromus.okanefinancespring.core.owners.dtos.GetOwnerDto
import com.xpromus.okanefinancespring.core.owners.mapper.fromCreateOwnerDto
import com.xpromus.okanefinancespring.core.owners.mapper.fromEditOwnerDto
import com.xpromus.okanefinancespring.core.owners.mapper.toGetOwnerDto
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class OwnerService(
    private val ownerRepository: OwnerRepository,
) {
    fun getOwnerById(id: UUID): Owner {
        return ownerRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Owner", id.toString())
            )
        }
    }

    fun getAllOwners(
        id: UUID?,
        firstName: String?,
        lastName: String?,
        birthday: Date?,
    ): List<GetOwnerDto> {
        val ownersToReturn = ownerRepository.findOwnersByFields(
            id, firstName, lastName, birthday
        )
        return ownersToReturn.map { toGetOwnerDto(it) }
    }

    fun createOwner(ownerDto: CreateOwnerDto): GetOwnerDto {
        val savedOwner = ownerRepository.save(fromCreateOwnerDto(ownerDto))
        return toGetOwnerDto(savedOwner)
    }

    fun updateOwner(id: UUID, editOwnerDto: EditOwnerDto): GetOwnerDto {
        return ownerRepository.findById(id).map {
            val save = ownerRepository.save(fromEditOwnerDto(it,editOwnerDto))
            toGetOwnerDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteOwner(id: UUID) {
        val toDeleteOwner = getOwnerById(id)
        ownerRepository.delete(toDeleteOwner)
    }
}