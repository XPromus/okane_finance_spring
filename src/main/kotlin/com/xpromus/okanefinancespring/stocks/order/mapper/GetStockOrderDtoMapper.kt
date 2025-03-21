package com.xpromus.okanefinancespring.stocks.order.mapper

import com.xpromus.okanefinancespring.stocks.order.StockOrder
import com.xpromus.okanefinancespring.stocks.order.dtos.GetStockOrderDto

fun toGetStockOrderDto(stockOrder: StockOrder): GetStockOrderDto {
    return GetStockOrderDto(
        id = stockOrder.id!!,
        isin = stockOrder.isin,
        wkn = stockOrder.wkn,
        tickerSymbol = stockOrder.tickerSymbol,
        stockName = stockOrder.stockName,
        numberOfStocks = stockOrder.numberOfStocks,
        buyInPrice = stockOrder.buyInPrice,
        fees = stockOrder.fees,
        tradeDate = stockOrder.tradeDate,
        targetWithdrawAccountID = stockOrder.targetWithdrawAccount.id!!,
        targetDepotID = stockOrder.targetDepot.id!!
    )
}
