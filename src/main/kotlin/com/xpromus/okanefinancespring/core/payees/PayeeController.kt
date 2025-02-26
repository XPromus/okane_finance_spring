package com.xpromus.okanefinancespring.core.payees

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
    ): List<Payee> {
        return payeeService.getAllPayees(id, payeeName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPayee(@RequestBody payee: PayeeDto): Payee {
        return payeeService.createPayee(payee)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePayee(
        @PathVariable id: UUID
    ) {
        payeeService.deletePayee(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updatePayee(
        @PathVariable id: UUID,
        @RequestBody payeeDto: PayeeDto
    ): Payee {
        return payeeService.updatePayee(payeeDto, id)
    }

}