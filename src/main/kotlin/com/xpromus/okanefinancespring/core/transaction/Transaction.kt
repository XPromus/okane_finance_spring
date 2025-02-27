package com.xpromus.okanefinancespring.core.transaction

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.core.categories.Category
import com.xpromus.okanefinancespring.core.payees.Payee
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
    val targetCategory: Category? = Category()
)