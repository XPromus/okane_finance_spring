package com.xpromus.okanefinancespring.core.payees

import com.xpromus.okanefinancespring.core.payees.dtos.CreatePayeeDto
import com.xpromus.okanefinancespring.core.payees.dtos.EditPayeeDto
import com.xpromus.okanefinancespring.core.payees.dtos.GetPayeeDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/payees")
class PayeeController(
    private val payeeService: PayeeService,
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getPayees(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "payeeName", required = false) payeeName: String?,
    ): List<GetPayeeDto> {
        return payeeService.getAllPayees(id, payeeName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPayee(@RequestBody createPayeeDto: CreatePayeeDto): GetPayeeDto {
        return payeeService.createPayee(createPayeeDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updatePayee(
        @PathVariable id: UUID,
        @RequestBody editPayeeDto: EditPayeeDto
    ): GetPayeeDto {
        return payeeService.updatePayee(id, editPayeeDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePayee(
        @PathVariable id: UUID
    ) {
        payeeService.deletePayee(id)
    }
}