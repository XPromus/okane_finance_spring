package com.xpromus.okanefinancespring.core.categories

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okanefinancespring.core.budgets.Budget
import com.xpromus.okanefinancespring.core.transaction.Transaction
import jakarta.persistence.*
import java.util.*

@Entity(name = "category")
@Table(name = "category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
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
    @JsonBackReference
    @JoinColumn(name = "parentCategory_id", referencedColumnName = "id")
    var parentCategory: Category? = null,
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "childCategory_id", referencedColumnName = "id")
    var childCategory: Category? = null,
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "budget_id")
    var targetBudget: Budget? = null
)