package com.xpromus.okanefinancespring.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "category")
@Table(name = "category")
class Category(
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
    val transactions: List<Transaction> = emptyList(),
    @OneToOne
    @JoinColumn(name = "parentCategory_id", referencedColumnName = "id")
    var parentCategory: Category? = null,
    @OneToOne
    @JoinColumn(name = "childCategory_id", referencedColumnName = "id")
    var childCategory: Category? = null,
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "budget_id")
    var targetBudget: Budget? = null
)