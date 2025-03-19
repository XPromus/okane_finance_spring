package com.xpromus.okanefinancespring.stocks.order

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.stocks.depot.Depot
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "stock_order")
class StockOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "isin", nullable = false)
    val isin: String = "",
    @Column(name = "wkn", nullable = false)
    val wkn: String = "",
    @Column(name = "ticker_symbol", nullable = false)
    val tickerSymbol: String = "",
    @Column(name = "stock_name", nullable = false)
    val stockName: String = "",
    @Column(name = "number_of_stocks", nullable = false)
    val numberOfStocks: Int = 0,
    @Column(name = "buy_in_price", nullable = false)
    val buyInPrice: Long = 0,
    @Column(name = "fees", nullable = false)
    val fees: Long = 0,
    @Column(name = "trade_date", nullable = false)
    val tradeDate: Date = Date(),
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val targetWithdrawAccount: Account = Account(),
    @ManyToOne
    @JoinColumn(name = "depot_id", nullable = false)
    val targetDepot: Depot = Depot()
)