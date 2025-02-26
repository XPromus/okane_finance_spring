package com.xpromus.okanefinancespring.stocks.depot

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
import java.util.UUID

@RestController
@RequestMapping("/depot")
class DepotController(
    private val depotService: DepotService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getDepots(
        @RequestParam(name = "id", required = false) id: UUID?
    ): List<Depot> {
        return depotService.getAllDepots(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDepot(
        @RequestBody depotDto: DepotDto
    ): Depot {
        return depotService.createDepot(depotDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDepot(
        @PathVariable id: UUID
    ) {
        depotService.deleteDepot(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateDepot(
        @PathVariable id: UUID,
        @RequestBody depotDto: DepotDto
    ): Depot {
        return depotService.updateDepot(depotDto, id)
    }

}