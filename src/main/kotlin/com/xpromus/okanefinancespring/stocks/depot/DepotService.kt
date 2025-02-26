package com.xpromus.okanefinancespring.stocks.depot

import com.xpromus.okanefinancespring.core.institute.InstituteService
import com.xpromus.okanefinancespring.core.owners.OwnerService
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
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
        id: UUID?
    ): List<Depot> {
        return depotRepository.findDepotsByField(id)
    }

    fun createDepot(depotDto: DepotDto): Depot {
        val depotOwner = ownerService.getOwnerById(UUID.fromString(depotDto.ownerId))
        val depotInstitute = instituteService.getInstituteById(UUID.fromString(depotDto.instituteId))
        return depotRepository.save(
            convertDepotDtoToDepot(depotOwner, depotInstitute)
        )
    }

    @Transactional
    fun deleteDepot(id: UUID) {
        val toDeleteDepot = getDepotById(id)
        depotRepository.delete(toDeleteDepot)
    }

    fun updateDepot(depotDto: DepotDto, id: UUID): Depot {
        return depotRepository.findById(id).map {
            val save = depotRepository.save(
                Depot(
                    id = it.id,
                    owner = ownerService.getOwnerById(UUID.fromString(depotDto.ownerId)),
                    institute = instituteService.getInstituteById(UUID.fromString(depotDto.instituteId)),
                    taxExemptionEntry = it.taxExemptionEntry,
                    stockOrders = it.stockOrders
                )
            )
            Depot(
                id = save.id,
                owner = save.owner,
                institute = save.institute,
                taxExemptionEntry = save.taxExemptionEntry,
                stockOrders = save.stockOrders
            )
        }.orElseGet(null)
    }

}