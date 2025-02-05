package com.xpromus.okane_finance_spring.payee

import com.xpromus.okane_finance_spring.transaction.Transaction
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "payee")
class Payee (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val payeeName: String = "",
    @OneToMany(
        mappedBy = "targetPayee",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = false,
        fetch = FetchType.EAGER
    )
    val transactions: List<Transaction> = emptyList()
)