package com.xpromus.okane_finance_spring.transaction.tag

import com.xpromus.okane_finance_spring.transaction.Transaction
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "tag")
class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val tagName: String = "",
    @ManyToMany(mappedBy = "targetTags")
    val transactions: List<Transaction> = emptyList()
)