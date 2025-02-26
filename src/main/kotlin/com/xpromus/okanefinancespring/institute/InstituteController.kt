package com.xpromus.okanefinancespring.institute

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
@RequestMapping("/institutes")
class InstituteController(
    private val instituteService: InstituteService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getInstitutes(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "name", required = false) name: String?
    ): List<Institute> {
        return instituteService.getAllInstitutes(id, name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstitute(
        @RequestBody instituteDto: InstituteDto
    ): Institute {
        return instituteService.createInstitute(instituteDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOwner(
        @PathVariable id: UUID
    ) {
        instituteService.deleteInstitute(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateInstitute(
        @PathVariable id: UUID,
        @RequestBody instituteDto: InstituteDto
    ): Institute {
        return instituteService.updateInstitute(instituteDto, id)
    }

}