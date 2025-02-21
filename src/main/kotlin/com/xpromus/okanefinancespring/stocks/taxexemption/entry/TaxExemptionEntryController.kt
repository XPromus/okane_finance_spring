package com.xpromus.okanefinancespring.stocks.taxexemption.entry

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
@RequestMapping("/tax-exemption-entry")
class TaxExemptionEntryController(
    private val taxExemptionEntryService: TaxExemptionEntryService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTaxExemptionEntryServices(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "value", required = false) value: Int?
    ): List<TaxExemptionEntry> {
        return taxExemptionEntryService.getAllTaxExemptionEntries(id, value)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTaxExemptionEntry(
        @RequestBody taxExemptionEntryDto: TaxExemptionEntryDto
    ): TaxExemptionEntry {
        return taxExemptionEntryService.createTaxExemptionEntry(taxExemptionEntryDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTaxExemptionEntry(
        @PathVariable id: UUID
    ) {
        taxExemptionEntryService.deleteTaxExemptionEntry(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTaxExemptionEntry(
        @PathVariable id: UUID,
        @RequestBody taxExemptionEntryDto: TaxExemptionEntryDto
    ): TaxExemptionEntry {
        return taxExemptionEntryService.updateTaxExemptionEntry(taxExemptionEntryDto, id)
    }

}