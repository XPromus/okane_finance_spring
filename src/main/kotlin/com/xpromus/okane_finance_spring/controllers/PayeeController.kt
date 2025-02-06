package com.xpromus.okane_finance_spring.controllers

import com.xpromus.okane_finance_spring.entities.Payee
import com.xpromus.okane_finance_spring.dto.PayeeDto
import com.xpromus.okane_finance_spring.services.PayeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePayee(
        @RequestParam(name = "id", required = true) id: UUID
    ) {
        payeeService.deletePayee(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updatePayee(
        @RequestBody payeeDto: PayeeDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): Payee {
        return payeeService.updatePayee(payeeDto, id)
    }

}