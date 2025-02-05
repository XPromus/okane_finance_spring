package com.xpromus.okane_finance_spring.transaction

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okane_finance_spring.account.Account
import com.xpromus.okane_finance_spring.payee.Payee
import com.xpromus.okane_finance_spring.transaction.category.Category
import com.xpromus.okane_finance_spring.transaction.tag.Tag
import jakarta.persistence.*
import java.util.Date
import java.util.UUID

@Entity(name = "transaction")
class Transaction (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val transactionName: String = "",
    @Column(nullable = false)
    val doneDate: Date = Date(),
    @Column(nullable = true)
    val finishedDate: Date = Date(),
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
    val targetCategory: Category = Category(),
    @ManyToMany
    @JoinTable(
        name = "transaction_tag",
        joinColumns = [JoinColumn(name = "transaction_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val targetTags: List<Tag> = emptyList()
)