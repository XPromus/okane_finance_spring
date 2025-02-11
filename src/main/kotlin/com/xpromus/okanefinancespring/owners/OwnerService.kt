package com.xpromus.okanefinancespring.owners

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
    ): List<Owner> {
        return ownerRepository.findOwnersByFields(
            id, firstName, lastName, birthday
        )
    }

    fun createOwner(ownerDto: OwnerDto): Owner {
        return ownerRepository.save(convertOwnerDtoToOwner(ownerDto))
    }

    @Transactional
    fun deleteOwner(id: UUID) {
        val toDeleteOwner = getOwnerById(id)
        ownerRepository.delete(toDeleteOwner)
    }

    fun updateOwner(ownerDto: OwnerDto, id: UUID): Owner {
        return ownerRepository.findById(id).map {
            val save = ownerRepository.save(
                Owner(
                    id = it.id,
                    firstName = ownerDto.firstName,
                    lastName = ownerDto.lastName,
                    birthday = ownerDto.birthday,
                    accounts = it.accounts
                )
            )
            Owner(
                id = save.id,
                firstName = save.firstName,
                lastName = save.lastName,
                birthday = save.birthday,
                accounts = save.accounts
            )
        }.orElseGet(null)
    }

}