package com.xpromus.okanefinancespring.core.accounts

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okanefinancespring.core.institute.Institute
import com.xpromus.okanefinancespring.core.owners.Owner
import com.xpromus.okanefinancespring.core.transactions.transaction.Transaction
import jakarta.persistence.*
import java.util.*

@Entity(name = "account")
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "accountName")
    val accountName: String = "",
    @Column(name = "startingBalance")
    val startingBalance: Long = 0,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "institute_id")
    val institute: Institute = Institute(),

    @OneToMany(
        mappedBy = "targetAccount",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val transactions: List<Transaction> = emptyList(),

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    val owner: Owner = Owner()
)