package com.xpromus.okanefinancespring.stocks.order.dtos

import java.util.Date
import java.util.UUID

data class GetStockOrderDto(
    val id: UUID,
    val isin: String,
    val wkn: String,
    val stockName: String,
    val numberOfStocks: Int,
    val buyInPrice: Long,
    val fees: Long,
    val tradeDate: Date,
    val targetDepotID: UUID
)
