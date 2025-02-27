package com.xpromus.okanefinancespring.core.institute

import com.xpromus.okanefinancespring.core.institute.dtos.CreateInstituteDto
import com.xpromus.okanefinancespring.core.institute.dtos.EditInstituteDto
import com.xpromus.okanefinancespring.core.institute.dtos.GetInstituteDto
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
    ): List<GetInstituteDto> {
        return instituteService.getAllInstitutes(id, name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstitute(
        @RequestBody createInstituteDto: CreateInstituteDto
    ): GetInstituteDto {
        return instituteService.createInstitute(createInstituteDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateInstitute(
        @PathVariable id: UUID,
        @RequestBody editInstituteDto: EditInstituteDto
    ): GetInstituteDto {
        return instituteService.updateInstitute(id, editInstituteDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOwner(
        @PathVariable id: UUID
    ) {
        instituteService.deleteInstitute(id)
    }
}