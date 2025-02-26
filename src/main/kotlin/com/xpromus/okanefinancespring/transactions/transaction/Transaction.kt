package com.xpromus.okanefinancespring.transactions.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.xpromus.okanefinancespring.accounts.Account
import com.xpromus.okanefinancespring.payees.Payee
import com.xpromus.okanefinancespring.sorting.categories.Category
import com.xpromus.okanefinancespring.sorting.tags.Tag
import jakarta.persistence.*
import java.util.*

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

    @ManyToOne
    @JsonIgnoreProperties("transaction")
    @JoinColumn(name = "account_id")
    val targetAccount: Account = Account(),
    @ManyToOne
    @JsonIgnoreProperties("transaction")
    @JoinColumn(name = "payee_id")
    val targetPayee: Payee = Payee(),
    @ManyToOne
    @JsonIgnoreProperties("transaction")
    @JoinColumn(name = "category_id")
    val targetCategory: Category? = Category(),
    @ManyToMany
    @JoinTable(
        name = "transaction_tag",
        joinColumns = [JoinColumn(name = "transaction_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    @JsonIgnoreProperties("transaction_tag")
    val targetTags: List<Tag> = emptyList(),
)