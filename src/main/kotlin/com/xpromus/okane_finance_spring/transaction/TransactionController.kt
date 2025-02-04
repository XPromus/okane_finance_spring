package com.xpromus.okane_finance_spring.transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController @Autowired constructor(
    private val transactionService: TransactionService
) {

    @GetMapping("/test")
    fun test(): String {
        return "Test"
    }

}