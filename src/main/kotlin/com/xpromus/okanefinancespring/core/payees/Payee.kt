package com.xpromus.okanefinancespring.core.payees

import com.xpromus.okanefinancespring.core.transaction.Transaction
import jakarta.persistence.*
import java.util.*

@Entity(name = "payee")
@Table(name = "payee")
class Payee(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
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