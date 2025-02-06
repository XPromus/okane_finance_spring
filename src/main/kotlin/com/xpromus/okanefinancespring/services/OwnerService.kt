package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.OwnerDto
import com.xpromus.okanefinancespring.dto.convertOwnerDtoToOwner
import com.xpromus.okanefinancespring.entities.Owner
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.repositories.OwnerRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class OwnerService(
    private val ownerRepository: OwnerRepository,
) {

    fun getOwnerById(id: UUID): Owner {
        return ownerRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Owner with id $id could not be found")
        }
    }

    fun getAllOwners(
        id: UUID?,
        firstName: String?,
        lastName: String?,
        birthday: Date?,
    ): List<Owner> {
        if ((id ?: firstName ?: lastName ?: birthday) != null) {
            return ownerRepository.findAllByIdAndFirstNameAndLastNameAndBirthday(
                id, firstName, lastName, birthday
            )
        }

        return ownerRepository.findAll()
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