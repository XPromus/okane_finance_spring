package com.xpromus.okane_finance_spring.transaction

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okane_finance_spring.account.Account
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.Date
import java.util.UUID

@Entity(name = "transaction")
class Transaction (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val doneDate: Date = Date(),
    @Column(nullable = true)
    val finishedDate: Date = Date(),
    @Column(nullable = false)
    val amount: Long = 0,
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "account_id")
    val targetAccount: Account = Account()
)