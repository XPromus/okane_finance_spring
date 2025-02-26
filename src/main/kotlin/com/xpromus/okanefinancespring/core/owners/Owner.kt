package com.xpromus.okanefinancespring.core.owners

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.stocks.depot.Depot
import jakarta.persistence.*
import java.util.*

@Entity(name = "owner")
@Table(name = "owner")
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "firstName", nullable = false)
    val firstName: String = "",
    @Column(name = "lastName", nullable = false)
    val lastName: String = "",
    @Column(name = "birthday", nullable = true)
    val birthday: Date? = Date(),
    @OneToMany(
        mappedBy = "owner",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = false,
        fetch = FetchType.LAZY
    )
    val accounts: List<Account> = emptyList(),
    @OneToMany(
        mappedBy = "owner",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = false,
        fetch = FetchType.LAZY
    )
    val depots: List<Depot> = emptyList()
)