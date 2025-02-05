package com.xpromus.okane_finance_spring.category

import com.xpromus.okane_finance_spring.transaction.Transaction
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "category")
class Category (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val categoryName: String = "",
    @OneToMany(
        mappedBy = "targetCategory",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = false,
        fetch = FetchType.EAGER
    )
    val transactions: List<Transaction> = emptyList()
)