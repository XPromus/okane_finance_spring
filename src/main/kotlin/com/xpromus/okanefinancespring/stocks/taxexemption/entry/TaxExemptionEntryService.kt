package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.stocks.depot.DepotService
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TaxExemptionEntryService(
    private val taxExemptionEntryRepository: TaxExemptionEntryRepository,
    private val depotService: DepotService
) {

    fun getTaxExemptionEntryById(id: UUID): TaxExemptionEntry {
        return taxExemptionEntryRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("TaxExemptionEntry", id.toString())
            )
        }
    }

    fun getAllTaxExemptionEntries(
        id: UUID?,
        value: Int?
    ): List<TaxExemptionEntry> {
        return taxExemptionEntryRepository.findTaxExemptionEntriesByFields(
            id, value
        )
    }

    fun createTaxExemptionEntry(taxExemptionEntryDto: TaxExemptionEntryDto): TaxExemptionEntry {
        val targetDepot = depotService.getDepotById(UUID.fromString(taxExemptionEntryDto.depotId))
        return taxExemptionEntryRepository.save(
            convertTaxExemptionEntryDtoToTaxExemptionEntry(taxExemptionEntryDto, targetDepot)
        )
    }

    @Transactional
    fun deleteTaxExemptionEntry(id: UUID) {
        val toDeleteTaxExemptionEntry = getTaxExemptionEntryById(id)
        taxExemptionEntryRepository.delete(toDeleteTaxExemptionEntry)
    }

    fun updateTaxExemptionEntry(taxExemptionEntryDto: TaxExemptionEntryDto, id: UUID): TaxExemptionEntry {
        return taxExemptionEntryRepository.findById(id).map {
            val save = taxExemptionEntryRepository.save(
                TaxExemptionEntry(
                    id = it.id,
                    taxValue = taxExemptionEntryDto.value,
                    depot = depotService.getDepotById(UUID.fromString(taxExemptionEntryDto.depotId))
                )
            )
            TaxExemptionEntry(
                id = save.id,
                taxValue = save.taxValue,
                depot = save.depot
            )
        }.orElseGet(null)
    }

}