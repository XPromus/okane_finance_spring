package com.xpromus.okane_finance_spring.account

import com.xpromus.okane_finance_spring.transaction.Transaction
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "account")
class Account (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Column(name = "accountName")
    val accountName: String = "",
    @OneToMany(
        mappedBy = "targetAccount",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    val transactions: List<Transaction> = emptyList()
)