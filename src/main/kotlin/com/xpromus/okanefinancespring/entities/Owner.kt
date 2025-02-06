package com.xpromus.okanefinancespring.entities

import jakarta.persistence.*
import java.util.Date
import java.util.UUID

@Entity(name = "owner")
@Table(name = "owner")
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
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
)