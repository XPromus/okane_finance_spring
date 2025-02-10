package com.xpromus.okanefinancespring.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.Date
import java.util.UUID

@Entity(name = "transaction")
@Table(name = "transaction")
class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val transactionName: String = "",
    @Column(nullable = false)
    val doneDate: Date = Date(),
    @Column(nullable = true)
    val finishedDate: Date? = Date(),
    @Column(nullable = false)
    val amount: Long = 0,
    @Column(nullable = false)
    val isRecurring: Boolean = false,
    @Column(nullable = true)
    val recurringDate: Date? = Date(),

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "account_id")
    val targetAccount: Account = Account(),
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "payee_id")
    val targetPayee: Payee = Payee(),
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id")
    val targetCategory: Category? = Category(),
    @ManyToMany
    @JoinTable(
        name = "transaction_tag",
        joinColumns = [JoinColumn(name = "transaction_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val targetTags: List<Tag> = emptyList()
)