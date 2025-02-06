package com.xpromus.okanefinancespring.entities

import jakarta.persistence.*
import java.util.UUID

@Entity(name = "budget")
@Table(name = "budget")
class Budget(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val budgetName: String = "",
    @Column(nullable = false)
    val maxValue: Long = 0,
    @OneToMany(
        mappedBy = "targetBudget",
        cascade = [CascadeType.ALL],
        orphanRemoval = false,
        fetch = FetchType.EAGER
    )
    val targetCategories: List<Category> = emptyList(),
)