package com.xpromus.okanefinancespring.accounts

import com.xpromus.okanefinancespring.institute.Institute
import com.xpromus.okanefinancespring.owners.Owner
import com.xpromus.okanefinancespring.transactions.transaction.Transaction
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
    @JoinColumn(name = "owner_id")
    val owner: Owner = Owner()
)