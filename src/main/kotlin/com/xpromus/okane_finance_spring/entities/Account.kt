package com.xpromus.okane_finance_spring.entities

import jakarta.persistence.*
import java.util.UUID

@Entity(name = "account")
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Column(name = "accountName")
    val accountName: String = "",
    @OneToMany(
        mappedBy = "targetAccount",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val transactions: List<Transaction> = emptyList()
)