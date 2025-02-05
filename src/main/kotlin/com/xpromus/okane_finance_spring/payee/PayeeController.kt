package com.xpromus.okane_finance_spring.payee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/payees")
class PayeeController @Autowired constructor(
    private val payeeService: PayeeService,
) {

    @GetMapping
    fun getPayees(
        @RequestParam(name = "id", required = false) id: UUID,
        @RequestParam(name = "payeeName", required = false) payeeName: String,
    ): ResponseEntity<List<Payee>> {
        return ResponseEntity<List<Payee>>(
            payeeService.getAllPayees(id, payeeName),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun createPayee(@RequestBody payee: PayeeDto): ResponseEntity<Payee> {
        return ResponseEntity<Payee>(
            payeeService.createPayee(payee),
            HttpStatus.OK
        )
    }

    @DeleteMapping
    fun deletePayee(
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<String> {
        val toDeletePayee = payeeService.getPayeeById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        payeeService.deletePayee(toDeletePayee)
        return ResponseEntity<String>(
            "Payee with id: $id has been deleted",
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updatePayee(
        @RequestBody payeeDto: PayeeDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<Payee> {
        return ResponseEntity<Payee>(
            payeeService.updatePayee(payeeDto, id),
            HttpStatus.OK
        )
    }

}