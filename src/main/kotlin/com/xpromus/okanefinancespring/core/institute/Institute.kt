package com.xpromus.okanefinancespring.core.institute

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.stocks.depot.Depot
import jakarta.persistence.*
import java.util.*

@Entity(name = "institute")
@Table(name = "institute")
class Institute (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "name", nullable = false)
    val name: String = "",
    @OneToMany(
        mappedBy = "institute",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = false,
        fetch = FetchType.LAZY
    )
    val accounts: List<Account> = emptyList(),
    @OneToMany(
        mappedBy = "institute",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = false,
        fetch = FetchType.LAZY
    )
    val depots: List<Depot> = emptyList()
)