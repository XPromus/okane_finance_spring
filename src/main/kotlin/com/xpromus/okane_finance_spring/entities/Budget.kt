package com.xpromus.okane_finance_spring.entities

import jakarta.persistence.*
import java.util.UUID

@Entity(name = "budget")
@Table(name = "budget")
class Budget(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
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