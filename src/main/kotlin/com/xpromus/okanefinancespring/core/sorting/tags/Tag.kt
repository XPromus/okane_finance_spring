package com.xpromus.okanefinancespring.core.sorting.tags

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okanefinancespring.core.transactions.transaction.Transaction
import jakarta.persistence.*
import java.util.*

@Entity(name = "tag")
@Table(name = "tag")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val tagName: String = "",
    @JsonBackReference
    @ManyToMany(mappedBy = "targetTags")
    val transactions: List<Transaction> = emptyList()
)