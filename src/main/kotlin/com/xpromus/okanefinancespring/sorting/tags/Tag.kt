package com.xpromus.okanefinancespring.sorting.tags

import com.xpromus.okanefinancespring.transactions.transaction.Transaction
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "tag")
@Table(name = "tag")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val tagName: String = "",
    @ManyToMany(mappedBy = "targetTags")
    val transactions: List<Transaction> = emptyList()
)