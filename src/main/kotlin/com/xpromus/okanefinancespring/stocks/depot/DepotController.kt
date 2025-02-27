package com.xpromus.okanefinancespring.stocks.depot

import com.xpromus.okanefinancespring.stocks.depot.dtos.CreateDepotDto
import com.xpromus.okanefinancespring.stocks.depot.dtos.EditDepotDto
import com.xpromus.okanefinancespring.stocks.depot.dtos.GetDepotDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/depot")
class DepotController(
    private val depotService: DepotService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getDepots(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "depotName", required = false) depotName: String?,
        @RequestParam(name = "institute", required = false) instituteID: UUID?,
        @RequestParam(name = "owner", required = false) ownerID: UUID?,
        @RequestParam(name = "taxExemptionEntry", required = false) taxExemptionEntryID: UUID?
    ): List<GetDepotDto> {
        return depotService.getAllDepots(
            id, depotName, instituteID, ownerID, taxExemptionEntryID
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDepot(
        @RequestBody createDepotDto: CreateDepotDto
    ): GetDepotDto {
        return depotService.createDepot(createDepotDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateDepot(
        @PathVariable id: UUID,
        @RequestBody editDepotDto: EditDepotDto
    ): GetDepotDto {
        return depotService.updateDepot(id, editDepotDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDepot(
        @PathVariable id: UUID
    ) {
        depotService.deleteDepot(id)
    }
}