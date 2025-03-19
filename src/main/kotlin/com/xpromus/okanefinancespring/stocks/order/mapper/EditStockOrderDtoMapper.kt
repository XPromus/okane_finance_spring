package com.xpromus.okanefinancespring.stocks.order.mapper

import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.order.StockOrder
import com.xpromus.okanefinancespring.stocks.order.dtos.EditStockOrderDto

fun fromEditStockOrderDto(
    stockOrder: StockOrder,
    targetDepot: Depot?,
    editStockOrderDto: EditStockOrderDto
): StockOrder {
    return StockOrder(
        id = stockOrder.id,
        isin = editStockOrderDto.isin ?: stockOrder.isin,
        wkn = editStockOrderDto.wkn ?: stockOrder.wkn,
        tickerSymbol = editStockOrderDto.tickerSymbol ?: stockOrder.tickerSymbol,
        stockName = editStockOrderDto.stockName ?: stockOrder.stockName,
        numberOfStocks = editStockOrderDto.numberOfStocks ?: stockOrder.numberOfStocks,
        buyInPrice = editStockOrderDto.buyInPrice ?: stockOrder.buyInPrice,
        fees = editStockOrderDto.fees ?: stockOrder.fees,
        tradeDate = editStockOrderDto.tradeDate ?: stockOrder.tradeDate,
        targetDepot = targetDepot ?: stockOrder.targetDepot
    )
}
