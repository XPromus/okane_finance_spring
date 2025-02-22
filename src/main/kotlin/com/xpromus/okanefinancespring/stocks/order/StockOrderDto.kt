package com.xpromus.okanefinancespring.stocks.order

import java.util.Date

data class StockOrderDto(
    val isin: String,
    val wkn: String,
    val stockName: String,
    val numberOfStocks: Int,
    val buyInPrice: Long,
    val fees: Long,
    val tradeDate: Date,
    val targetDepotId: String
)
