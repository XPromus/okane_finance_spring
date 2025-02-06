package com.xpromus.okanefinancespring.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.UUID

@Entity(name = "account")
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "accountName")
    val accountName: String = "",
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