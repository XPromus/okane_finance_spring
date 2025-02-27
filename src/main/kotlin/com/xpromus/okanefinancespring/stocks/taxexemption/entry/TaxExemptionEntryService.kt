package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.stocks.depot.DepotService
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.CreateTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.EditTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.GetTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.mapper.fromCreateTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.mapper.fromEditTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.mapper.toGetTaxExemptionEntryDto
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

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
        value: Int?,
        depotID: UUID?
    ): List<GetTaxExemptionEntryDto> {
        val taxExemptionEntryToReturn = taxExemptionEntryRepository.findTaxExemptionEntriesByFields(
            id, value, depotID
        )
        return taxExemptionEntryToReturn.map { toGetTaxExemptionEntryDto(it) }
    }

    fun createTaxExemptionEntry(
        createTaxExemptionEntryDto: CreateTaxExemptionEntryDto
    ): GetTaxExemptionEntryDto {
        val targetDepot = depotService.getDepotById(createTaxExemptionEntryDto.depotID)
        val newTaxExemptionEntry = taxExemptionEntryRepository.save(
            fromCreateTaxExemptionEntryDto(targetDepot, createTaxExemptionEntryDto)
        )
        return toGetTaxExemptionEntryDto(newTaxExemptionEntry)
    }

    fun updateTaxExemptionEntry(
        id: UUID,
        editTaxExemptionEntryDto: EditTaxExemptionEntryDto
    ): GetTaxExemptionEntryDto {
        val targetDepot = if (editTaxExemptionEntryDto.depotID == null) {
            null
        } else {
            depotService.getDepotById(editTaxExemptionEntryDto.depotID)
        }

        return taxExemptionEntryRepository.findById(id).map {
            val save = taxExemptionEntryRepository.save(
                fromEditTaxExemptionEntryDto(it, targetDepot, editTaxExemptionEntryDto)
            )
            toGetTaxExemptionEntryDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteTaxExemptionEntry(id: UUID) {
        val toDeleteTaxExemptionEntry = getTaxExemptionEntryById(id)
        taxExemptionEntryRepository.delete(toDeleteTaxExemptionEntry)
    }
}