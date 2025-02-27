package com.xpromus.okanefinancespring.stocks.depot

import com.xpromus.okanefinancespring.core.institute.InstituteService
import com.xpromus.okanefinancespring.core.owners.OwnerService
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.stocks.depot.dtos.*
import com.xpromus.okanefinancespring.stocks.depot.mapper.fromCreateDepotDto
import com.xpromus.okanefinancespring.stocks.depot.mapper.fromEditDepotDto
import com.xpromus.okanefinancespring.stocks.depot.mapper.toGetDepotDto
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class DepotService(
    private val depotRepository: DepotRepository,
    private val ownerService: OwnerService,
    private val instituteService: InstituteService
) {
    fun getDepotById(id: UUID): Depot {
        return depotRepository.findById(id).orElseThrow{
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Depot", id.toString())
            )
        }
    }

    fun getAllDepots(
        id: UUID?,
        instituteID: UUID?,
        ownerID: UUID?,
        taxExemptionEntryID: UUID?
    ): List<GetDepotDto> {
        val depotsToReturn = depotRepository.findDepotsByField(
            id, instituteID, ownerID, taxExemptionEntryID
        )
        return depotsToReturn.map { toGetDepotDto(it) }
    }

    fun createDepot(createDepotDto: CreateDepotDto): GetDepotDto {
        val createDepotClassesDto = CreateDepotClassesDto(
            institute = instituteService.getInstituteById(createDepotDto.instituteID),
            owner = ownerService.getOwnerById(createDepotDto.ownerID)
        )
        val newDepot = depotRepository.save(fromCreateDepotDto(createDepotClassesDto))
        return toGetDepotDto(newDepot)
    }

    fun updateDepot(id: UUID, editDepotDto: EditDepotDto): GetDepotDto {
        val editDepotClassesDto = EditDepotClassesDto(
            institute = if (editDepotDto.instituteID == null) {
                null
            } else {
                instituteService.getInstituteById(editDepotDto.instituteID)
            },
            owner = if (editDepotDto.ownerID == null) {
                null
            } else {
                ownerService.getOwnerById(editDepotDto.ownerID)
            }
        )

        return depotRepository.findById(id).map {
            val save = depotRepository.save(
                fromEditDepotDto(it, editDepotClassesDto)
            )
            toGetDepotDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteDepot(id: UUID) {
        val toDeleteDepot = getDepotById(id)
        depotRepository.delete(toDeleteDepot)
    }
}