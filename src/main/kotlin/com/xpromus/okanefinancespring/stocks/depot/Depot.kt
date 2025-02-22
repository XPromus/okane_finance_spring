package com.xpromus.okanefinancespring.stocks.depot

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xpromus.okanefinancespring.institute.Institute
import com.xpromus.okanefinancespring.owners.Owner
import com.xpromus.okanefinancespring.stocks.order.StockOrder
import com.xpromus.okanefinancespring.stocks.taxexemption.entry.TaxExemptionEntry
import jakarta.persistence.*
import java.util.*

@Entity(name = "depot")
@Table(name = "depot")
class Depot (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "institute_id")
    val institute: Institute = Institute(),
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    val owner: Owner = Owner(),
    @OneToOne(mappedBy = "depot")
    val taxExemptionEntry: TaxExemptionEntry? = null,
    @OneToMany(
        mappedBy = "targetDepot",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val stockOrders: List<StockOrder> = emptyList()
)