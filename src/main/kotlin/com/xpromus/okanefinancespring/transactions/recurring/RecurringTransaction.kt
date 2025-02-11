package com.xpromus.okanefinancespring.transactions.recurring

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okanefinancespring.accounts.Account
import com.xpromus.okanefinancespring.sorting.categories.Category
import com.xpromus.okanefinancespring.payees.Payee
import com.xpromus.okanefinancespring.sorting.tags.Tag
import jakarta.persistence.*
import java.util.*

@Entity(name = "recurring_transaction")
@Table(name = "recurring_transaction")
class RecurringTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val transactionName: String = "",
    @Column(nullable = false)
    val amount: Long = 0,

    @Column(nullable = true)
    val dayOfTheMonth: Int? = 1,
    @Column(nullable = true)
    val monthInterval: Int? = 1,

    @Column(nullable = true)
    val dayOfTheWeek: Int? = 0,
    @Column(nullable = true)
    val weekInterval: Int? = 0,

    @Column(nullable = true)
    val recurringUntil: Date? = null,

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