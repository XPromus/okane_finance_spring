package com.xpromus.okanefinancespring.stocks.order.dtos

import java.util.*

data class EditStockOrderDto(
    val isin: String?,
    val wkn: String?,
    val tickerSymbol: String?,
    val stockName: String?,
    val numberOfStocks: Int?,
    val buyInPrice: Long?,
    val fees: Long?,
    val tradeDate: Date?,
    val targetWithdrawAccountID: UUID?,
    val targetDepotID: UUID?
)
