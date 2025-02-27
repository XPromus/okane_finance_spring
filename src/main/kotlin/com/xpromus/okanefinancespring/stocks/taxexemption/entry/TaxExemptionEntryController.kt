package com.xpromus.okanefinancespring.stocks.taxexemption.entry

import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.CreateTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.EditTaxExemptionEntryDto
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.dtos.GetTaxExemptionEntryDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/tax-exemption/entry")
class TaxExemptionEntryController(
    private val taxExemptionEntryService: TaxExemptionEntryService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTaxExemptionEntryServices(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "value", required = false) value: Int?,
        @RequestParam(name = "depotID", required = false) depotID: UUID?
    ): List<GetTaxExemptionEntryDto> {
        return taxExemptionEntryService.getAllTaxExemptionEntries(id, value, depotID)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTaxExemptionEntry(
        @RequestBody createTaxExemptionEntryDto: CreateTaxExemptionEntryDto
    ): GetTaxExemptionEntryDto {
        return taxExemptionEntryService.createTaxExemptionEntry(createTaxExemptionEntryDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTaxExemptionEntry(
        @PathVariable id: UUID,
        @RequestBody editTaxExemptionEntryDto: EditTaxExemptionEntryDto
    ): GetTaxExemptionEntryDto {
        return taxExemptionEntryService.updateTaxExemptionEntry(id, editTaxExemptionEntryDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTaxExemptionEntry(
        @PathVariable id: UUID
    ) {
        taxExemptionEntryService.deleteTaxExemptionEntry(id)
    }
}