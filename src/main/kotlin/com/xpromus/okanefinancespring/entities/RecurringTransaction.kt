package com.xpromus.okanefinancespring.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "recurring_transaction")
class RecurringTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
)