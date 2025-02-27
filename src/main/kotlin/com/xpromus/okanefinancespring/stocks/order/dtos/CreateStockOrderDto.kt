package com.xpromus.okanefinancespring.stocks.order.dtos

import java.util.*

data class CreateStockOrderDto(
    val isin: String,
    val wkn: String,
    val stockName: String,
    val numberOfStocks: Int,
    val buyInPrice: Long,
    val fees: Long,
    val tradeDate: Date,
    val targetDepotID: UUID
)
