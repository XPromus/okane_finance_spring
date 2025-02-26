package com.xpromus.okanefinancespring.core.owners

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/owners")
class OwnerController(
    private val ownerService: OwnerService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getOwners(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "firstName", required = false) firstName: String?,
        @RequestParam(name = "lastName", required = false) lastName: String?,
        @RequestParam(name = "birthday", required = false) birthday: Date?,
    ): List<OwnerDto> {
        return ownerService.getAllOwners(id, firstName, lastName, birthday)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOwner(
        @RequestBody ownerDto: OwnerDto,
    ): UUID {
        return ownerService.createOwner(ownerDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOwner(
        @PathVariable id: UUID
    ) {
        ownerService.deleteOwner(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateOwner(
        @PathVariable id: UUID,
        @RequestBody ownerDto: OwnerDto,
    ): OwnerDto {
        return ownerService.updateOwner(ownerDto, id)
    }

}