package com.xpromus.okanefinancespring.stocks.taxexemption.maximum

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tax-exemption/maximum")
class TaxExemptionMaximumController(
    private val taxExemptionMaximumService: TaxExemptionMaximumService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getMaximumStatus(): GetTaxExemptionMaximumDto {
        return taxExemptionMaximumService.getMaximumStatus()
    }
}